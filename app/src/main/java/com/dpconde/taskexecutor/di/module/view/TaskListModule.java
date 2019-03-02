package com.dpconde.taskexecutor.di.module.view;

import android.content.Context;

import com.dpconde.taskexecutor.di.module.ContextModule;
import com.dpconde.taskexecutor.di.module.TaskModule;
import com.dpconde.taskexecutor.mvp.data.api.TestDataManager;
import com.dpconde.taskexecutor.mvp.view.tasklist.TaskListPresenter;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dpconde on 29/9/18.
 */

@Module(includes = {TaskModule.class, ContextModule.class})
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
