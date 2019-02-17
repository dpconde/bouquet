package com.dpconde.taskexecutor.mvp.data.api.greendao;

import android.os.AsyncTask;

import com.dpconde.taskexecutor.mvp.data.api.Callback;
import com.dpconde.taskexecutor.mvp.data.api.TestDataManager;
import com.dpconde.taskexecutor.mvp.data.model.DaoSession;
import com.dpconde.taskexecutor.mvp.data.model.Task;
import com.dpconde.taskexecutor.mvp.data.model.User;

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
    public void loadTasks(Callback callback) {

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
}
