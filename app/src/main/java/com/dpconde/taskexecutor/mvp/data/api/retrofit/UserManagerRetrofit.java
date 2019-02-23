package com.dpconde.taskexecutor.mvp.data.api.retrofit;

import com.dpconde.taskexecutor.mvp.view.checklistlist.ChecklistListCallback;
import com.dpconde.taskexecutor.mvp.data.api.UserManager;
import com.dpconde.taskexecutor.mvp.data.model.User;
import com.dpconde.taskexecutor.mvp.view.login.LoginCallback;
import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

/**
 * Created by dpconde on 28/9/18.
 */

public class UserManagerRetrofit implements UserManager {

    private LoginApi loginApi;
    private UserManager userManagerDB;

    public UserManagerRetrofit(LoginApi loginApi) {
        this.loginApi = loginApi;
    }

    @Override
    public void doLogin(String userCode, String password, LoginCallback callback) {

        //TODO Obtener le usuario de la API, si va bien, lo guarda en DB, si no, mostrar error.

        //Encrypt password to save it in the database
        String encryptedPassword = Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();

        //TODO delete
        User user = new User();
        user.setPassword(encryptedPassword);
        user.setUserCode(userCode);
        user.setEmail("dpconde.me@gmail.com");

        //Save user in DataBase
        User userCreated = userManagerDB.createUser(user);

        callback.onDoLoginSuccess(userCreated);

    }

    @Override
    public boolean deleteUser(String userId) {
        return false;
    }

    @Override
    public void getAllUsers(final ChecklistListCallback checklistListCallback) {
    }

    @Override
    public User getUserById(Long userId) {
        return null;
    }

    @Override
    public User createUser(User user) {
        return null;
    }

    public void setUserManagerDB(UserManager userManagerDB) {
        this.userManagerDB = userManagerDB;
    }
}
