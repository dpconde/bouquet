package com.dpconde.taskexecutor.mvp.data.api;

import com.dpconde.taskexecutor.mvp.data.model.Task;
import com.dpconde.taskexecutor.mvp.data.model.User;

import java.util.List;

/**
 * Created by dpconde on 28/9/18.
 */

public interface TestDataManager {


    /**
     * Load tasks
     * @param callback
     */
    void loadTasks(Callback callback);

    /**
     * Delete task
     * @param taskId
     * @return
     */
    boolean deleteTask(long taskId);


    /**
     * Edit task
     * @param task
     * @return
     */
    boolean editTask(Task task);


    /**
     * Push task to server
     * @param task
     * @return
     */
    boolean pushTask(Task task);




}