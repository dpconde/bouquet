package com.dpconde.taskexecutor.di.component;

import com.dpconde.taskexecutor.di.module.view.TaskListModule;
import com.dpconde.taskexecutor.mvp.view.tasklist.TaskListActivity;
import com.dpconde.taskexecutor.di.module.TaskModule;
import com.dpconde.taskexecutor.mvp.view.tasklist.UserDetailPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by dpconde on 28/9/18.
 */

@Singleton
@Component(modules = {TaskModule.class, TaskListModule.class}, dependencies = {AppComponent.class})
public interface TaskListComponent {

    void inject(TaskListActivity taskListActivity);
    UserDetailPresenter getDetailPresenter();
}