package com.dpconde.taskexecutor.mvp.data.api.retrofit;

import android.util.Log;

import com.dpconde.taskexecutor.mvp.data.api.Callback;
import com.dpconde.taskexecutor.mvp.data.api.TestDataManager;
import com.dpconde.taskexecutor.mvp.data.api.UserManager;
import com.dpconde.taskexecutor.mvp.data.model.ApiResponse;
import com.dpconde.taskexecutor.mvp.data.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by dpconde on 28/9/18.
 */

public class UserManagerRetrofit implements UserManager {

    private UsersApi usersApi;

    public UserManagerRetrofit(UsersApi usersApi) {
        this.usersApi = usersApi;
    }

    @Override
    public boolean deleteUser(String userId) {
        //TODO
        return false;
    }

    @Override
    public void getAllUsers(final Callback callback) {
        /*
        usersApi.getUsers(NUM_RESULTS, SEED).enqueue(new retrofit2.Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                List<User> userList = response.body().getResults();
                if(callback!=null){
                    callback.onRetrievedUsers(userList);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.e("RETROFIT", t.toString());
            }
        });
        */
    }

    @Override
    public User getUserById(Long userId) {
        //TODO
        return null;
    }

    @Override
    public User createUser(User user) {
        //TODO
        return null;
    }

    @Override
    public User doLogin(String userCode, String password) {
        return null;
    }

}
