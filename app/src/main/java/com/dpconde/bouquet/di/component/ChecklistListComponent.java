package com.dpconde.bouquet.di.component;

import com.dpconde.bouquet.di.module.view.ChecklistListModule;
import com.dpconde.bouquet.mvp.view.checklistlist.ChecklistListActivity;
import com.dpconde.bouquet.di.module.DataProviderModule;
import com.dpconde.bouquet.mvp.view.checklistlist.ChecklistListPresenter;

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
