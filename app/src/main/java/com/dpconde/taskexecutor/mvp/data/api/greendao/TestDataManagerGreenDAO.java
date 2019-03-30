package com.dpconde.taskexecutor.mvp.data.api.greendao;

import com.dpconde.taskexecutor.mvp.Constants;
import com.dpconde.taskexecutor.mvp.data.model.Checklist;
import com.dpconde.taskexecutor.mvp.data.model.TaskDao;
import com.dpconde.taskexecutor.mvp.data.model.TaskType;
import com.dpconde.taskexecutor.mvp.data.model.TaskTypeDao;
import com.dpconde.taskexecutor.mvp.view.checklistlist.ChecklistListCallback;
import com.dpconde.taskexecutor.mvp.data.api.TestDataManager;
import com.dpconde.taskexecutor.mvp.data.model.DaoSession;
import com.dpconde.taskexecutor.mvp.data.model.Task;

import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dpconde on 28/9/18.
 */

public class TestDataManagerGreenDAO implements TestDataManager {

    TestDataManager userDataManagerRetrofit;
    DaoSession daoSession;

    public TestDataManagerGreenDAO(TestDataManager userDataManagerRetrofit, DaoSession daoSession) {
        this.userDataManagerRetrofit = userDataManagerRetrofit;
        this.daoSession = daoSession;
    }


    @Override
    public void loadTasks(ChecklistListCallback checklistListCallback) {
    }

    @Override
    public boolean deleteTask(long taskId) {
        return false;
    }

    @Override
    public boolean editTask(Task task) {
        return false;
    }

    @Override
    public boolean pushTask(Task task) {
        return false;
    }

    @Override
    public void loadChecklists(ChecklistListCallback checklistListCallback) {
        List<Checklist> checklistList = daoSession.getChecklistDao().loadAll();

        if(checklistList==null || checklistList.isEmpty()){
            checklistList = new ArrayList<>();
            Checklist chk;
            for(int i= 0; i<20; i++){
                chk = new Checklist();
                chk.setId(new Long(i));
                chk.setDescription("Description" + i);
                chk.setProjectName("Project" + i);
                chk.setSamples("Samples" + i);
                chk.setWorkingOrder("BRK180810");
                checklistList.add(chk);
            }
        }

        checklistListCallback.onRetrievedChecklists(checklistList);
    }

    @Override
    public Checklist loadChecklist(ChecklistListCallback checklistListCallback, long checklistID) {
        checklistID = 129343L;
        return daoSession.getChecklistDao().load(checklistID);
    }

    @Override
    public Checklist saveChecklist(Checklist checklist){
        Long checklistID = daoSession.getChecklistDao().insert(checklist);

        for(Task task: checklist.getTasks()){

            //Save type first
            Long taskTypeId = daoSession.getTaskTypeDao().insertOrReplace(task.getTaskType());
            task.setTaskTypeId(taskTypeId);

            task.setChecklistId(checklistID);
            daoSession.getTaskDao().insert(task);
        }

        return checklist;
    }



    /**
     * Get test types by Checklist ID
     * @param checklistId
     * @return
     */
    public List<TaskType> getTaskTypesByChecklistId(long checklistId){
        QueryBuilder<TaskType> queryBuilder = daoSession.getTaskTypeDao().queryBuilder();
        queryBuilder
                .distinct()
                .where(TaskTypeDao.Properties.Id.notEq(Constants.TASK_TYPE_CONTAINER_ID))
                .join(Task.class, TaskDao.Properties.TaskTypeId)
                .where(TaskDao.Properties.ChecklistId.eq(checklistId));

        return queryBuilder.list();
    }

    public List<Task> getFirstLevelTasks(long checklistId){
        QueryBuilder<Task> queryBuilder = daoSession.getTaskDao().queryBuilder();
        queryBuilder
                .where(TaskDao.Properties.ParentTaskId.eq(0L))
                .where(TaskDao.Properties.ChecklistId.eq(checklistId));

        return queryBuilder.list();
    }

    /**
     * Get filtered tasks
     * @param filters
     * @return
     */
    public List<Task> getFilteredTasks(Map<String, Object> filters, long checklistId, List<Task> childrenTasks){
        QueryBuilder<Task> queryBuilder = daoSession.getTaskDao().queryBuilder();
        queryBuilder.where(TaskDao.Properties.ChecklistId.eq(checklistId));

        if(filters!=null){
            if(filters.containsKey(Constants.STATUS_FILTER_KEY)){
                if(!filters.get(Constants.STATUS_FILTER_KEY).equals("all")){
                    queryBuilder.where(TaskDao.Properties.ExecutionStatus.eq(filters.get(Constants.STATUS_FILTER_KEY)));
                }
            }

            if(filters.containsKey(Constants.SYNC_FILTER_KEY)){
                if(!filters.get(Constants.SYNC_FILTER_KEY).equals("all")){
                    queryBuilder.where(TaskDao.Properties.SyncStatus.eq(filters.get(Constants.SYNC_FILTER_KEY)));
                }

            }

            if(filters.containsKey(Constants.TYPE_FILTER_KEY)){
                TaskType taskType = (TaskType) filters.get(Constants.TYPE_FILTER_KEY);
                if(taskType.getId() != -1L){
                    queryBuilder.where(TaskDao.Properties.TaskTypeId.eq(taskType.getId()));
                }

            }
        }

        if(!childrenTasks.isEmpty()){
            queryBuilder.where(queryBuilder.or(TaskDao.Properties.ParentTaskId.eq(0L), TaskDao.Properties.ParentTaskId.in(childrenTasks)));
        }else{
            queryBuilder.where(TaskDao.Properties.ParentTaskId.eq(0L));
        }

        queryBuilder.orderAsc(TaskDao.Properties.Order);
        List<Task> result = queryBuilder.list();

        return result;
    }

    private List<Long> getChildrenTaskIdsByTaskId(long taskId){

        List<Long> childrenTasks = new ArrayList<>();

        QueryBuilder<Task> queryBuilder = daoSession.getTaskDao().queryBuilder();
        queryBuilder.where(TaskDao.Properties.ParentTaskId.eq(taskId));
        childrenTasks.add(taskId);

        List<Task> result = queryBuilder.list();
        for(Task task: result){
            if(task.getTaskType().getId() == Constants.TASK_TYPE_CONTAINER_ID){
                childrenTasks.add(task.getId());
                childrenTasks.addAll(getChildrenTaskIdsByTaskId(task.getId()));
            }
        }

        return childrenTasks;
    }
}
