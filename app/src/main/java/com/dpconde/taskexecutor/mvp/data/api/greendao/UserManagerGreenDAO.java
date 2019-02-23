package com.dpconde.taskexecutor.mvp.data.api.greendao;

import com.dpconde.taskexecutor.mvp.data.api.retrofit.UserManagerRetrofit;
import com.dpconde.taskexecutor.mvp.view.checklistlist.ChecklistListCallback;
import com.dpconde.taskexecutor.mvp.data.api.UserManager;
import com.dpconde.taskexecutor.mvp.data.model.DaoSession;
import com.dpconde.taskexecutor.mvp.data.model.User;
import com.dpconde.taskexecutor.mvp.data.model.UserDao;
import com.dpconde.taskexecutor.mvp.view.login.LoginCallback;
import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Created by dpconde on 28/9/18.
 */

public class UserManagerGreenDAO implements UserManager {


    private DaoSession daoSession;
    private UserManager userManagerAPI;

    public UserManagerGreenDAO(DaoSession daoSession, UserManager userManagerAPI) {
        this.daoSession = daoSession;
        this.userManagerAPI = userManagerAPI;
    }


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
        Long userId = daoSession.insert(user);
        user.setId(userId);

        return user;
    }


    @Override
    public void doLogin(String userCode, String password, LoginCallback callback) {

        //The user must be persisted in order to do login
        User user = this.getUserByUserCode(userCode);

        if(user == null){

            //User not found, we call API to get user
            ((UserManagerRetrofit)userManagerAPI).setUserManagerDB(this);
            userManagerAPI.doLogin(userCode, password, callback);

        }else{

            //Encrypt password to compare it with the database
            String encryptedPassword = Hashing.sha256()
                    .hashString(password, StandardCharsets.UTF_8)
                    .toString();

            //If password matches, then login success
            if(user.getPassword().equals(encryptedPassword)){
                callback.onDoLoginSuccess(user);
            }else{
                callback.onDoLoginFail(); //TODO add message
            }

        }
    }

    private User getUserByUserCode(String userCode){

        List<User> users = daoSession.getUserDao().queryBuilder()
                .where(UserDao.Properties.UserCode.eq(userCode))
                .list();

        return users.isEmpty() ? null : users.get(0);
    }
}
