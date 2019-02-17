package com.dpconde.taskexecutor.mvp.view.checklistlist;

import android.content.Context;
import android.content.Intent;

import com.dpconde.taskexecutor.mvp.data.api.Callback;
import com.dpconde.taskexecutor.mvp.data.api.TestDataManager;
import com.dpconde.taskexecutor.mvp.data.model.User;
import com.dpconde.taskexecutor.mvp.view.tasklist.TaskListActivity;

import java.util.List;

/**
 * Created by dpconde on 28/9/18.
 */

public class ChecklistListPresenter implements Callback{

    //Dependencies
    private View view;
    private TestDataManager userDataManager;
    private Context context;

    //Page management
    private final int PAGE_SIZE = 10;
    private int currentPage = 1;
    private int numMaxPages;


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
     * @param user
     */
    public void onItemClicked(User user) {
        Intent intent = new Intent(context, TaskListActivity.class);
        intent.putExtra("userIntent", user.getId());
        context.startActivity(intent);
    }

    @Override
    public void onRetrievedUsers(List<User> userList) {
        view.refreshUserList(userList);
        view.hideProgress();
    }


    /**
     * Load next page users
     */
    public void loadNextUsers(){
        currentPage++;
        loadData();
    }


    /**
     * Load previous page users
     */
    public void loadPrevUsers(){
        currentPage--;
        loadData();
    }

    /**
     * Check current page in order to disable/enable previous and next page buttons
     */
    public void checkCurrentPage(){
        if(currentPage==1){
            view.enablePrevButton(false);
            view.enableNextButton(true);
        }else if(currentPage==numMaxPages){
            view.enableNextButton(false);
            view.enablePrevButton(true);
        }else{
            view.enablePrevButton(true);
            view.enableNextButton(true);
        }
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
         * @param userList
         */
        void refreshUserList(List<User> userList);

        /**
         * Enable/disable previous page button
         * @param enable
         */
        void enablePrevButton(boolean enable);

        /**
         * Enable/disable next page button
         * @param enable
         */
        void enableNextButton(boolean enable);
    }
}
