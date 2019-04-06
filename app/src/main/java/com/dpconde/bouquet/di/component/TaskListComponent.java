package com.dpconde.bouquet.di.component;

import com.dpconde.bouquet.di.module.view.TaskListModule;
import com.dpconde.bouquet.mvp.view.tasklist.TaskListActivity;
import com.dpconde.bouquet.di.module.DataProviderModule;
import com.dpconde.bouquet.mvp.view.tasklist.TaskListPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by dpconde on 28/9/18.
 */

@Singleton
@Component(modules = {DataProviderModule.class, TaskListModule.class}, dependencies = {AppComponent.class})
public interface TaskListComponent {

    void inject(TaskListActivity taskListActivity);
    TaskListPresenter getDetailPresenter();
}
