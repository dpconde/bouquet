package com.dpconde.taskexecutor.mvp.data.api.retrofit;

import com.dpconde.taskexecutor.mvp.data.api.retrofit.responseobjects.ResponseDTO;
import com.dpconde.taskexecutor.mvp.data.model.LoginParams;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by dpconde on 28/9/18.
 */

public interface LoginApi {

    @Headers({"version: 2.0.0", "Content-Type: application/json"})
    @POST("login")
    Call<ResponseDTO> login(@Body LoginParams loginParams);

}
