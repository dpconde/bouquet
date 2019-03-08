package com.dpconde.taskexecutor.di.module;

import com.dpconde.taskexecutor.di.module.datasource.GreenDAOModule;
import com.dpconde.taskexecutor.di.module.datasource.RetrofitModule;
import com.dpconde.taskexecutor.mvp.data.api.UserManager;
import com.dpconde.taskexecutor.mvp.data.api.greendao.UserManagerGreenDAO;
import com.dpconde.taskexecutor.mvp.data.api.jsonreader.UserManagerJson;
import com.dpconde.taskexecutor.mvp.data.api.retrofit.LoginApi;
import com.dpconde.taskexecutor.mvp.data.api.retrofit.UserManagerRetrofit;
import com.dpconde.taskexecutor.mvp.data.model.DaoSession;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * Created by dpconde on 28/9/18.
 */

@Module(includes = {RetrofitModule.class, GreenDAOModule.class})
public class UserModule {


    @Provides
    @Named("db")
    @Singleton
    UserManager provideUserManagerDB(DaoSession daoSession){
        return new UserManagerGreenDAO(daoSession);
    }


    @Provides
    @Named("api")
    @Singleton
    UserManager provideUserManagerAPI(LoginApi loginApi){
        return new UserManagerRetrofit(loginApi);
    }


    @Provides
    @Named("json")
    @Singleton
    UserManager provideUserManagerJSON(){
        return new UserManagerJson();
    }

}
