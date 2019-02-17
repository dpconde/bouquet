package com.dpconde.taskexecutor.di.module;

import com.dpconde.taskexecutor.di.module.datasource.GreenDAOModule;
import com.dpconde.taskexecutor.di.module.datasource.RetrofitModule;
import com.dpconde.taskexecutor.mvp.data.api.TestDataManager;
import com.dpconde.taskexecutor.mvp.data.api.UserManager;
import com.dpconde.taskexecutor.mvp.data.api.greendao.TestDataManagerGreenDAO;
import com.dpconde.taskexecutor.mvp.data.api.jsonreader.UserManagerJson;
import com.dpconde.taskexecutor.mvp.data.api.retrofit.TestDataManagerRetrofit;
import com.dpconde.taskexecutor.mvp.data.api.retrofit.UsersApi;
import com.dpconde.taskexecutor.mvp.data.model.DaoSession;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * Created by dpconde on 28/9/18.
 */

@Module(includes = {RetrofitModule.class, GreenDAOModule.class})
public class TaskModule {

    //TODO añadir más providers para cada fuente de datos

    @Provides
    @Named("local")
    @Singleton
    TestDataManager provideUserDataManagerLocal(@Named("online") TestDataManager taskDataManager, DaoSession daoSession){
        return new TestDataManagerGreenDAO(taskDataManager, daoSession);
    }


    @Provides
    @Named("online")
    @Singleton
    TestDataManager provideUserDataManagerOnline(UsersApi usersApi){
        return new TestDataManagerRetrofit(usersApi);
    }


    @Provides
    @Named("json")
    @Singleton
    UserManager provideUserManagerJSON(){
        return new UserManagerJson();
    }

}
