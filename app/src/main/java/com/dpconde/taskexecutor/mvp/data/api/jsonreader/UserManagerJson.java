package com.dpconde.taskexecutor.mvp.data.api.jsonreader;

import com.dpconde.taskexecutor.mvp.view.checklistlist.ChecklistListCallback;
import com.dpconde.taskexecutor.mvp.data.api.UserManager;
import com.dpconde.taskexecutor.mvp.data.model.User;
import com.dpconde.taskexecutor.mvp.view.login.LoginCallback;

/**
 * Created by dpconde on 13/02/2019.
 */

public class UserManagerJson implements UserManager {


    @Override
    public boolean deleteUser(String userId) {
        return false;
    }

    @Override
    public void getAllUsers(ChecklistListCallback checklistListCallback) {
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
    public void doLogin(String userCode, String password, LoginCallback callback) {
    }
}
