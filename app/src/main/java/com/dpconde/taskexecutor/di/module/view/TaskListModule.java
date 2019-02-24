package com.dpconde.taskexecutor.di.module.view;

import android.content.Context;

import com.dpconde.taskexecutor.di.module.ContextModule;
import com.dpconde.taskexecutor.di.module.TaskModule;
import com.dpconde.taskexecutor.mvp.data.api.TestDataManager;
import com.dpconde.taskexecutor.mvp.view.tasklist.UserDetailPresenter;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dpconde on 29/9/18.
 */

@Module(includes = {TaskModule.class, ContextModule.class})
public class TaskListModule {

    private UserDetailPresenter.View view;

    public TaskListModule(UserDetailPresenter.View view) {
        this.view = view;
    }

    @Provides
    public UserDetailPresenter.View provideView() {
        return view;
    }

    @Provides
    public UserDetailPresenter providePresenter(UserDetailPresenter.View categoryView,
                                                @Named("db") TestDataManager userDataManager,
                                                Context context) {
        return new UserDetailPresenter(categoryView, userDataManager, context);
    }

}
