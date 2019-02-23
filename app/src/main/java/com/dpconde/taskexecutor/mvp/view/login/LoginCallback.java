package com.dpconde.taskexecutor.mvp.view.login;

import com.dpconde.taskexecutor.mvp.data.model.User;


/**
 * Created by dpconde on 29/9/18.
 */

public interface LoginCallback {

    /**
     * Method to manage user login success
     * @param user
     */
    void onDoLoginSuccess(User user);

    /**
     * Method to manage user login fail
     */
    void onDoLoginFail();
}
