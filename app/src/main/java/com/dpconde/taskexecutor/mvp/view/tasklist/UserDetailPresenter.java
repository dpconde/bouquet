package com.dpconde.taskexecutor.mvp.view.tasklist;

import android.content.Context;

import com.dpconde.taskexecutor.mvp.data.api.TestDataManager;
import com.dpconde.taskexecutor.mvp.data.model.User;

/**
 * Created by dpconde on 28/9/18.
 */

public class UserDetailPresenter {

    //Dependencies
    private View view;
    private TestDataManager userDataManager;
    private Context context;

    private User currentUser;

    public UserDetailPresenter(UserDetailPresenter.View view, TestDataManager userDataManager, Context context) {
        this.view = view;
        this.userDataManager = userDataManager;
        this.context = context;
    }




    public User getCurrentUser(){
        return currentUser;
    }


    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }


    public interface View {

        /**
         * Show message to user
         * @param message
         */
        void showMessage(String message);

        /**
         * Finish current activity
         */
        void finishView();
    }

}
