package com.dpconde.taskexecutor.mvp.view.login;

import android.content.Context;
import android.content.Intent;

import com.dpconde.taskexecutor.mvp.view.checklistlist.ChecklistListCallback;
import com.dpconde.taskexecutor.mvp.data.api.UserManager;
import com.dpconde.taskexecutor.mvp.data.model.Checklist;
import com.dpconde.taskexecutor.mvp.data.model.User;
import com.dpconde.taskexecutor.mvp.view.checklistlist.ChecklistListActivity;

import java.util.List;

/**
 * Created by dpconde on 28/9/18.
 */

public class LoginPresenter implements LoginCallback {

    //Dependencies
    private View view;
    private Context context;

    //Business
    private UserManager userManagerDB;


    public LoginPresenter(View view, Context context, UserManager userManagerDB) {
        this.view = view;
        this.context = context;
        this.userManagerDB = userManagerDB;
    }


    /**
     * Start Checklist list activity
     * @param user
     */
    public void startChecklistListActivity(User user) {
        Intent intent = new Intent(context, ChecklistListActivity.class);
        context.startActivity(intent);
    }


    /**
     * Read userCode and Password fields and try to do login
     * @param userCode
     * @param password
     */
    public void doLogin(String userCode, String password){

        //Try to do login locally
        userManagerDB.doLogin(userCode, password, this);
    }

    @Override
    public void onDoLoginSuccess(User user) {
        startChecklistListActivity(user);
    }

    @Override
    public void onDoLoginFail() {

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
         * Show error message. Red Color
         */
        void showErrorMessage(String message);

        /**
         * Show warning message. Yellow Color
         */
        void showWarningMessage(String message);

        /**
         * Show info message. Green Color
         */
        void showInfoMessage(String message);

    }
}
