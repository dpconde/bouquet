package com.dpconde.bouquet.mvp.view.login;

import com.dpconde.bouquet.mvp.data.model.User;


/**
 * Created by dpconde on 29/9/18.
 */

public interface LoginCallback {

    /**
     * Method to manage user login online success
     * @param user
     */
    void onOnlineLoginSuccess(User user);

    /**
     * Method to manage user login offline success
     * @param user
     */
    void onOfflineLoginSuccess(User user);

    /**
     * Method to manage user login online fail
     */
    void onOnlineLoginFail(User user);

    /**
     * Method to manage user login offline fail
     */
    void onOfflineLoginFail(int errorMessage);

}
