package com.dpconde.taskexecutor.di.component;

import com.dpconde.taskexecutor.di.module.view.ChecklistListModule;
import com.dpconde.taskexecutor.mvp.view.checklistlist.ChecklistListActivity;
import com.dpconde.taskexecutor.di.module.DataProviderModule;
import com.dpconde.taskexecutor.mvp.view.checklistlist.ChecklistListPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by dpconde on 28/9/18.
 */

@Singleton
@Component(modules = {DataProviderModule.class, ChecklistListModule.class}, dependencies = {AppComponent.class})
public interface ChecklistListComponent {

    void inject(ChecklistListActivity checklistListActivity);
    ChecklistListPresenter getMainPresenter();
}
