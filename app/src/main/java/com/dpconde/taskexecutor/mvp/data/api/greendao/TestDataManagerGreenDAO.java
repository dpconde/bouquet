package com.dpconde.taskexecutor.mvp.data.api.greendao;

import com.dpconde.taskexecutor.mvp.data.model.Checklist;
import com.dpconde.taskexecutor.mvp.view.checklistlist.ChecklistListCallback;
import com.dpconde.taskexecutor.mvp.data.api.TestDataManager;
import com.dpconde.taskexecutor.mvp.data.model.DaoSession;
import com.dpconde.taskexecutor.mvp.data.model.Task;

import java.util.ArrayList;
import java.util.List;

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

        checklistListCallback.onRetrievedChecklist(checklistList);

    }
}
