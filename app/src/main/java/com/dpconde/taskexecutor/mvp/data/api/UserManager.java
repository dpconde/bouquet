package com.dpconde.taskexecutor.mvp.data.api;

import com.dpconde.taskexecutor.mvp.data.model.User;

import java.util.List;

/**
 * Created by dpconde on 13/02/2019.
 */

public interface UserManager {


    /**
     * Delete user
     * @param userId
     * @return sucess
     */
    boolean deleteUser(String userId);


    /**
     * Get all users
     * @param callback
     */
    void getAllUsers(Callback callback);


    /**
     * Get user by ID
     * @param userId
     * @return the retrieved user
     */
    User getUserById(Long userId);


    /**
     * Create a new user
     * @param user
     * @return the user created with the persisted details
     */
    User createUser(User user);


    /**
     * Get the user by its userCode and password
     * @param userCode
     * @param password
     * @return
     */
    User doLogin(String userCode, String password);


}
