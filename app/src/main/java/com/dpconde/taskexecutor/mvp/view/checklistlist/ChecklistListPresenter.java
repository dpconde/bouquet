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
    private TestDataManager userDataManager;
    private Context context;


    public ChecklistListPresenter(View view, TestDataManager userDataManager, Context context) {
        this.view = view;
        this.userDataManager = userDataManager;
        this.context = context;
    }

    /**
     * Retrieve users from business
     */
    public void loadData(){
        view.showProgress();
       // userDataManager.loadData(this, currentPage, PAGE_SIZE);
       // numMaxPages = (int) Math.ceil(userDataManager.countTotalResults()/PAGE_SIZE);
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

    @Override
    public void onRetrievedChecklist(List<Checklist> checklistList) {
        view.refreshChecklistList(checklistList);
        view.hideProgress();
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
