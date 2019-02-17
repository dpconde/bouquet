package com.dpconde.taskexecutor.mvp.data.api.jsonreader;

import com.dpconde.taskexecutor.mvp.data.api.Callback;
import com.dpconde.taskexecutor.mvp.data.api.TestDataManager;
import com.dpconde.taskexecutor.mvp.data.api.UserManager;
import com.dpconde.taskexecutor.mvp.data.model.User;

import java.util.List;

/**
 * Created by dpconde on 13/02/2019.
 */

public class UserManagerJson implements UserManager {


    @Override
    public boolean deleteUser(String userId) {
        return false;
    }

    @Override
    public void getAllUsers(Callback callback) {

    }

    @Override
    public User getUserById(Long userId) {
        return null;
    }

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public User doLogin(String userCode, String password) {
        return null;
    }
}
