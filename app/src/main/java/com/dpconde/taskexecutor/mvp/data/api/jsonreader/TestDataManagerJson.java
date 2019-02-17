package com.dpconde.taskexecutor.mvp.data.api.jsonreader;

import android.content.Context;

import com.dpconde.taskexecutor.R;
import com.dpconde.taskexecutor.mvp.data.api.Callback;
import com.dpconde.taskexecutor.mvp.data.api.TestDataManager;
import com.dpconde.taskexecutor.mvp.data.model.Task;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by dpconde on 13/02/2019.
 */

public class TestDataManagerJson implements TestDataManager {

    private Context context;
    private Gson gson;


    public TestDataManagerJson (Context context, Gson gson){
        this.context = context;
        this.gson = gson;
    }


    @Override
    public void loadTasks(Callback callback) {
        try {
            InputStream inputStream = context.getResources().openRawResource(R.raw.login_response_ok);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, bytes.length);
            String json = new String(bytes);

            Task myModel = new Gson().fromJson(json, Task.class);

            //TODO
            //use callback

        } catch (IOException e) {

        }
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
