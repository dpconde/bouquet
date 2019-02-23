package com.dpconde.taskexecutor.di.module.datasource;

import dagger.Module;
import dagger.Provides;

import com.dpconde.taskexecutor.mvp.data.api.retrofit.LoginApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Named;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dpconde on 28/9/18.
 */

@Module
public class RetrofitModule {

    /**
     * **********************************************************************
     * Login providers
     * **********************************************************************
     */

    @Provides
    public LoginApi loginAPI(@Named("login") Retrofit retrofit){
        return retrofit.create(LoginApi.class);
    }

    @Provides
    @Named("login")
    public Retrofit retrofitLogin(GsonConverterFactory gsonConverterFactory){
        return new Retrofit.Builder()
                .baseUrl("https://intranet.idiada.com/verema-ws/service/casWS/")
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    /**
     * **********************************************************************
     */



    /**
     * **********************************************************************
     * Task providers
     * **********************************************************************
     */

    @Provides
    @Named("tasks")
    public LoginApi tasksAPI(@Named("tasks")Retrofit retrofit){
        return retrofit.create(LoginApi.class);
    }


    @Provides
    @Named("tasks")
    public Retrofit retrofitTasks(GsonConverterFactory gsonConverterFactory){
        return new Retrofit.Builder()
                .baseUrl("https://intranet.idiada.com/verema-ws/service/casWS/")
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    /**
     * **********************************************************************
     */




    /**
     * **********************************************************************
     * Common providers
     * **********************************************************************
     */

    @Provides
    public Gson gson(){
        GsonBuilder gsonBuilder  = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .setLenient();

        return gsonBuilder.create();
    }


    @Provides
    public GsonConverterFactory gsonConverterFactory(Gson gson){
        return GsonConverterFactory.create(gson);
    }

    /**
     * **********************************************************************
     */
}
