package com.dpconde.bouquet.mvp.data.api.retrofit;

import android.util.Base64;

import com.dpconde.bouquet.mvp.data.api.retrofit.responseobjects.login.ResponseDTO;
import com.dpconde.bouquet.mvp.data.model.LoginParams;
import com.dpconde.bouquet.mvp.view.checklistlist.ChecklistListCallback;
import com.dpconde.bouquet.mvp.data.api.UserManager;
import com.dpconde.bouquet.mvp.data.model.User;
import com.dpconde.bouquet.mvp.view.login.LoginCallback;

import java.io.UnsupportedEncodingException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by dpconde on 28/9/18.
 */

public class UserManagerRetrofit implements UserManager {

    private LoginApi loginApi;

    public UserManagerRetrofit(LoginApi loginApi) {
        this.loginApi = loginApi;
    }

    @Override
    public void doLogin(final String userCode, final String password, final LoginCallback callback) {

        //Create Login Params object
        LoginParams lp = new LoginParams();
        lp.setUsername(toBase64(userCode));
        lp.setPassword(toBase64(password));
        lp.setService(toBase64("https://intranet.idiada.com/verema-ws/")); //TODO meterlo en un properties

        //Create response user
        final User user = new User();
        user.setUserCode(userCode);
        user.setPassword(password);

        Call<ResponseDTO> repos = loginApi.login(lp);
        repos.enqueue(new Callback<ResponseDTO>() {
              @Override
              public void onResponse(Call<ResponseDTO> call, Response<ResponseDTO> response) {
                  ResponseDTO loginResponse = response.body();

                  if(loginResponse.getErrors().isEmpty()){
                      callback.onOnlineLoginSuccess(user);
                  }else {
                      callback.onOnlineLoginFail(user);
                  }
              }

            @Override
            public void onFailure(Call<ResponseDTO> call, Throwable t) {
                callback.onOnlineLoginFail(user);
            }
        });

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


    /**
     * Convert String to BASE64
     * @param value
     * @return
     */
    private String toBase64(String value){
        byte[] data = new byte[0];
        try {
            data = value.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return Base64.encodeToString(data, Base64.DEFAULT);
    }
}
