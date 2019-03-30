package com.dpconde.taskexecutor.mvp.view.login;

import android.content.Context;
import android.content.Intent;

import com.dpconde.taskexecutor.mvp.data.api.UserManager;
import com.dpconde.taskexecutor.mvp.data.model.User;
import com.dpconde.taskexecutor.mvp.view.checklistlist.ChecklistListActivity;

/**
 * Created by dpconde on 28/9/18.
 */

public class LoginPresenter implements LoginCallback {

    //Dependencies
    private View view;
    private Context context;

    //Business
    private UserManager userManagerDB;
    private UserManager userManagerAPI;


    public LoginPresenter(View view, Context context, UserManager userManagerAPI, UserManager userManagerDB) {
        this.view = view;
        this.context = context;
        this.userManagerDB = userManagerDB;
        this.userManagerAPI = userManagerAPI;
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
     * Read userCode and Password fields and try to do login online
     * @param userCode
     * @param password
     */
    public void doOnlineLogin(String userCode, String password){
        view.showLoading();
        userManagerAPI.doLogin(userCode, password, this);
    }


    /**
     * Read userCode and Password fields and try to do login offline
     * @param userCode
     * @param password
     */
    public void doOfflineLogin(String userCode, String password){
        userManagerDB.doLogin(userCode, password, this);
    }

    @Override
    public void onOnlineLoginSuccess(User user) {
        User userSaved = userManagerDB.createUser(user);

        if(userSaved != null){
            doOfflineLogin(user.getUserCode(), user.getPassword());
        }

    }

    @Override
    public void onOfflineLoginSuccess(User user) {
        view.hideLoading();
        startChecklistListActivity(user);
    }

    @Override
    public void onOnlineLoginFail(User user) {
        view.hideLoading();
        doOfflineLogin(user.getUserCode(), user.getPassword());

    }

    @Override
    public void onOfflineLoginFail(int errorMessage) {
        view.hideLoading();
        view.showMessage(errorMessage);
    }


    public interface View {

        /**
         * Show loading image and hide user list
         */
        void showLoading();

        /**
         * Hide loading image and show user list
         */
        void hideLoading();

        /**
         * Show message
         * @param messageResource
         */
        void showMessage(int messageResource);

    }
}
