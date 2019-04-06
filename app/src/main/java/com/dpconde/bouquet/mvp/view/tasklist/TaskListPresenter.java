package com.dpconde.bouquet.mvp.view.tasklist;

import android.content.Context;
import android.content.Intent;

import com.dpconde.bouquet.mvp.Constants;
import com.dpconde.bouquet.mvp.data.api.TestDataManager;
import com.dpconde.bouquet.mvp.data.api.greendao.TestDataManagerGreenDAO;
import com.dpconde.bouquet.mvp.data.model.Checklist;
import com.dpconde.bouquet.mvp.data.model.Task;
import com.dpconde.bouquet.mvp.data.model.TaskType;
import com.dpconde.bouquet.mvp.view.task.misuse.MisuseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dpconde on 28/9/18.
 */

public class TaskListPresenter {

    //Dependencies
    private View view;
    private TestDataManager testDataManager;
    private Context context;
    private long currentChecklistId;

    //Children Tasks
    private List<Task> childrenTask;


    public TaskListPresenter(TaskListPresenter.View view, TestDataManager userDataManager, Context context) {
        this.view = view;
        this.testDataManager = userDataManager;
        this.context = context;
        childrenTask = new ArrayList<>();
    }


    public void filterTasks(Map<String, Object> filters){

        List<Task> filteredTasks = ((TestDataManagerGreenDAO)testDataManager)
                .getFilteredTasks(filters, currentChecklistId, childrenTask);
        view.refreshList(filteredTasks, isUserFiltering(filters));
    }


    public void onItemClicked(Task task) {

        if(task.getTaskType().getId() == Constants.TASK_TYPE_CONTAINER_ID){

            ((TestDataManagerGreenDAO)testDataManager).getFirstLevelTasks(task.getId());

            if(childrenTask.contains(task)){
                childrenTask.remove(task);
            }else{
                childrenTask.add(task);
            }
            filterTasks(null); //no filters to apply
        }else{

            Intent intent;

            //MISUSE Task type
            if(task.getTaskType().getId() == Constants.TASK_TYPE_MISUSE_ID){
                intent = new Intent(context, MisuseActivity.class);
                intent.putExtra("misuseTaskIntent", task.getId());
            } else {
                return;
            }
            context.startActivity(intent);
        }

    }

    public Checklist getChecklistById(Long checklistId) {
        Checklist checklist = testDataManager.loadChecklist(null, checklistId);

        return checklist;
    }

    public List<TaskType> getTaskTypesByChecklistId(){
        List<TaskType> taskTypes = ((TestDataManagerGreenDAO)testDataManager).getTaskTypesByChecklistId(currentChecklistId);
        return taskTypes;
    }

    public void setCurrentChecklistId(long currentChecklistId) {
        this.currentChecklistId = currentChecklistId;
    }

    private boolean isUserFiltering(Map<String, Object> filters){

        if(filters != null){
            if(filters.containsKey(Constants.STATUS_FILTER_KEY)){
                if(!filters.get(Constants.STATUS_FILTER_KEY).equals("all")){
                    return true;
                }
            }

            if(filters.containsKey(Constants.SYNC_FILTER_KEY)){
                if(!filters.get(Constants.SYNC_FILTER_KEY).equals("all")){
                    return true;
                }
            }

            if(filters.containsKey(Constants.TYPE_FILTER_KEY)){
                TaskType taskType = (TaskType) filters.get(Constants.TYPE_FILTER_KEY);
                if(taskType.getId() != -1L){
                    return true;
                }
            }
        }

        return false;
    }

    public void updateFilteringMenuIcon(Map<String, Object> filters) {
        view.changeFilteringIcon(isUserFiltering(filters));
    }

    public List<Task> getFirstLevelTasks(Long checklistId) {
        return ((TestDataManagerGreenDAO)testDataManager).getFirstLevelTasks(checklistId);
    }


    public interface View {

        /**
         * Show message to user
         * @param message
         */
        void showMessage(String message);

        /**
         * Finish current activity
         */
        void finishView();

        /**
         * Refresh list
         * @param list
         */
        void refreshList(List<Task> list, boolean withFilters);

        /**
         * Change icon for filtering tasks
         * @param filtering
         */
        void changeFilteringIcon(boolean filtering);
    }

}
