package com.dpconde.bouquet.di.module;

import android.content.Context;

import com.dpconde.bouquet.di.module.datasource.GreenDAOModule;
import com.dpconde.bouquet.di.module.datasource.RetrofitModule;
import com.dpconde.bouquet.mvp.data.api.TestDataManager;
import com.dpconde.bouquet.mvp.data.api.greendao.TestDataManagerGreenDAO;
import com.dpconde.bouquet.mvp.data.api.jsonreader.TestDataManagerJson;
import com.dpconde.bouquet.mvp.data.api.retrofit.LoginApi;
import com.dpconde.bouquet.mvp.data.api.retrofit.TestDataManagerRetrofit;
import com.dpconde.bouquet.mvp.data.model.DaoSession;
import com.google.gson.Gson;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * Created by dpconde on 28/9/18.
 */

@Module(includes = {RetrofitModule.class, GreenDAOModule.class, ContextModule.class, GsonModule.class})
public class DataProviderModule {


    @Provides
    @Named("db")
    @Singleton
    TestDataManager provideTestDataManagerLocal(@Named("api") TestDataManager taskDataManager, DaoSession daoSession){
        return new TestDataManagerGreenDAO(taskDataManager, daoSession);
    }


    @Provides
    @Named("api")
    @Singleton
    TestDataManager provideTestDataManagerOnline(LoginApi loginApi){
        return new TestDataManagerRetrofit(loginApi);
    }


    @Provides
    @Named("json")
    @Singleton
    TestDataManager provideTestManagerJSON(Context context, Gson gson){
        return new TestDataManagerJson(context, gson);
    }

}
