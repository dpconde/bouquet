package com.dpconde.taskexecutor.mvp.data.api.retrofit;

import com.dpconde.taskexecutor.mvp.data.model.ApiResponse;
import com.dpconde.taskexecutor.mvp.data.model.LoginParams;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by dpconde on 28/9/18.
 */

public interface LoginApi {

    @POST("login/")
    Call<ApiResponse> login(@Body LoginParams loginParams);

}
