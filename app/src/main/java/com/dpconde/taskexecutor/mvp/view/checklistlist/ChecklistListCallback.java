package com.dpconde.taskexecutor.mvp.view.checklistlist;

import com.dpconde.taskexecutor.mvp.data.model.Checklist;
import com.dpconde.taskexecutor.mvp.data.model.User;

import java.util.List;

/**
 * Created by dpconde on 29/9/18.
 */

public interface ChecklistListCallback {

    /**
     * Method to manage retrieved checklists
     * @param checklistList
     */
    void onRetrievedChecklists(List<Checklist> checklistList);

    /**
     * Method to manage retrieved checklist
     * @param checklist
     */
    void onRetrievedChecklist(Checklist checklist);
}
