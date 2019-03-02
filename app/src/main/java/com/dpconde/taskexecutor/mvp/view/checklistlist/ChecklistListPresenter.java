package com.dpconde.taskexecutor.mvp.view.checklistlist;

import android.content.Context;
import android.content.Intent;

import com.dpconde.taskexecutor.mvp.data.api.TestDataManager;
import com.dpconde.taskexecutor.mvp.data.model.Checklist;
import com.dpconde.taskexecutor.mvp.view.tasklist.TaskListActivity;

import java.util.List;

/**
 * Created by dpconde on 28/9/18.
 */

public class ChecklistListPresenter implements ChecklistListCallback {

    //Dependencies
    private View view;
    private TestDataManager testDataManagerDB;
    private TestDataManager testDataManagerJson;
    private Context context;


    public ChecklistListPresenter(View view, TestDataManager testDataManagerDB, TestDataManager testDataManagerJson, Context context) {
        this.view = view;
        this.testDataManagerDB = testDataManagerDB;
        this.testDataManagerJson = testDataManagerJson;
        this.context = context;
    }

    /**
     * Retrieve users from business
     */
    public void loadData(){
        view.showProgress();
        testDataManagerDB.loadChecklists(this);
    }

    /**
     * Start user detail Activity for the selected user
     * @param checklist
     */
    public void onItemClicked(Checklist checklist) {
        Intent intent = new Intent(context, TaskListActivity.class);
        intent.putExtra("checklistIntent", checklist.getId());
        context.startActivity(intent);
    }

    public void readChecklist(long checklistID){
        testDataManagerJson.loadChecklist(this, checklistID);
    }

    @Override
    public void onRetrievedChecklists(List<Checklist> checklistList) {
        view.refreshChecklistList(checklistList);
        view.hideProgress();
    }

    @Override
    public void onRetrievedChecklist(Checklist checklist) {
        testDataManagerDB.saveChecklist(checklist);
        testDataManagerDB.loadChecklists(this);
    }



    public interface View {

        /**
         * Show loading image and hide user list
         */
        void showProgress();

        /**
         * Hide loading image and show user list
         */
        void hideProgress();

        /**
         * Refresh the user list with the given user list
         * @param checklistList
         */
        void refreshChecklistList(List<Checklist> checklistList);
    }
}
