package com.dpconde.bouquet.mvp.view.checklistlist;

import com.dpconde.bouquet.mvp.data.model.Checklist;

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
