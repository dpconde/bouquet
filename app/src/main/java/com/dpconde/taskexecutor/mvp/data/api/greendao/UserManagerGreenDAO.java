package com.dpconde.taskexecutor.mvp.data.api.greendao;

import com.dpconde.taskexecutor.mvp.data.api.Callback;
import com.dpconde.taskexecutor.mvp.data.api.UserManager;
import com.dpconde.taskexecutor.mvp.data.model.User;
import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * Created by dpconde on 28/9/18.
 */

public class UserManagerGreenDAO implements UserManager {


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

        //The user must be persisted in order to do login
        User user = this.getUserByUsercode(userCode);

        if(user == null){
            return null;
        }

        //Encrypt password to compare it with the database
        String encryptedPassword = Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();

        if(user.getPassword().equals(encryptedPassword)){
            return user;
        }

        return null;
    }

    private User getUserByUsercode(String usercode){
        return null;
    }
}
