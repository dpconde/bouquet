package com.dpconde.taskexecutor.mvp.data.api.greendao;

import com.dpconde.taskexecutor.R;
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

    public UserManagerGreenDAO(DaoSession daoSession) {
        this.daoSession = daoSession;
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

        //Encrypt password
        String encryptedPassword = Hashing.sha256()
                .hashString(user.getPassword(), StandardCharsets.UTF_8)
                .toString();

        user.setPassword(encryptedPassword);

        Long userId = daoSession.insertOrReplace(user);
        user.setId(userId);

        return user;
    }


    @Override
    public void doLogin(String userCode, String password, LoginCallback callback) {

        //The user must be persisted in order to do login
        User user = this.getUserByUserCode(userCode);

        if(user == null){

            //User not found, error
            callback.onOfflineLoginFail(R.string.login_message_login_fail_user_offline);

        }else{

            //Encrypt password to compare it with the database
            String encryptedPassword = Hashing.sha256()
                    .hashString(password, StandardCharsets.UTF_8)
                    .toString();

            //If password matches, then login success
            if(user.getPassword().equals(encryptedPassword)
                    || user.getPassword().equals(password)){
                callback.onOfflineLoginSuccess(user);
            }else{
                callback.onOfflineLoginFail(R.string.login_message_login_fail_password);
            }

        }
    }

    /**
     * Method to retrieve user by its userCode
     * @param userCode
     * @return
     */
    private User getUserByUserCode(String userCode){

        List<User> users = daoSession.getUserDao().queryBuilder()
                .where(UserDao.Properties.UserCode.eq(userCode))
                .list();

        return users.isEmpty() ? null : users.get(0);
    }
}
