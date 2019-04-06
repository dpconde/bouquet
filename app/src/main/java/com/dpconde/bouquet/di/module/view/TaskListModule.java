package com.dpconde.bouquet.di.module.view;

import android.content.Context;

import com.dpconde.bouquet.di.module.ContextModule;
import com.dpconde.bouquet.di.module.DataProviderModule;
import com.dpconde.bouquet.mvp.data.api.TestDataManager;
import com.dpconde.bouquet.mvp.view.tasklist.TaskListPresenter;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dpconde on 29/9/18.
 */

@Module(includes = {DataProviderModule.class, ContextModule.class})
public class TaskListModule {

    private TaskListPresenter.View view;

    public TaskListModule(TaskListPresenter.View view) {
        this.view = view;
    }

    @Provides
    public TaskListPresenter.View provideView() {
        return view;
    }

    @Provides
    public TaskListPresenter providePresenter(TaskListPresenter.View categoryView,
                                              @Named("db") TestDataManager userDataManager,
                                              Context context) {
        return new TaskListPresenter(categoryView, userDataManager, context);
    }

}
