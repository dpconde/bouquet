package com.dpconde.taskexecutor.di.module.view;

import android.content.Context;

import com.dpconde.taskexecutor.di.module.ContextModule;
import com.dpconde.taskexecutor.di.module.DataProviderModule;
import com.dpconde.taskexecutor.mvp.data.api.TestDataManager;
import com.dpconde.taskexecutor.mvp.view.checklistlist.ChecklistListPresenter;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dpconde on 29/9/18.
 */

@Module(includes = {DataProviderModule.class, ContextModule.class})
public class ChecklistListModule {

    private ChecklistListPresenter.View view;

    public ChecklistListModule(ChecklistListPresenter.View view) {
        this.view = view;
    }

    @Provides
    public ChecklistListPresenter.View provideView() {
        return view;
    }

    @Provides
    public ChecklistListPresenter providePresenter(ChecklistListPresenter.View categoryView,
                                                   @Named("db") TestDataManager testDataManagerDB,
                                                   @Named("json") TestDataManager testDataManagerJson,
                                                   Context context) {
        return new ChecklistListPresenter(categoryView, testDataManagerDB, testDataManagerJson, context);
    }

}
