package com.dpconde.bouquet.mvp.data.api;

import com.dpconde.bouquet.mvp.data.model.Checklist;
import com.dpconde.bouquet.mvp.data.model.Task;
import com.dpconde.bouquet.mvp.view.checklistlist.ChecklistListCallback;

/**
 * Created by dpconde on 28/9/18.
 */

public interface TestDataManager {


    /**
     * Load tasks
     * @param checklistListCallback
     */
    void loadTasks(ChecklistListCallback checklistListCallback);

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


    /**
     * Load checklists
     * @param checklistListCallback
     */
    void loadChecklists(ChecklistListCallback checklistListCallback);

    /**
     * Load checklist
     * @param checklistListCallback
     * @return
     */
    Checklist loadChecklist(ChecklistListCallback checklistListCallback, long checklistID);

    /**
     * Method to save Checklist
     * @param checklist
     * @return
     */
    Checklist saveChecklist(Checklist checklist);
}
