package com.dpconde.taskexecutor.di.module.datasource;


import android.content.Context;

import com.dpconde.taskexecutor.di.module.ContextModule;
import com.dpconde.taskexecutor.mvp.data.model.DaoMaster;
import com.dpconde.taskexecutor.mvp.data.model.DaoSession;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dpconde on 29/9/18.
 */

@Module(includes = ContextModule.class)
public class JsonDataSourceModule {

    @Provides
    @Singleton
    public DaoSession providesJsonDataSource(Context context){
        return new DaoMaster(
                new DaoMaster.DevOpenHelper(context, "greendao_demo.db").getWritableDb()).newSession();
    }
}
