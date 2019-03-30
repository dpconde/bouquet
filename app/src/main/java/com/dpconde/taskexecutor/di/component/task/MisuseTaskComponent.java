package com.dpconde.taskexecutor.di.component.task;

import com.dpconde.taskexecutor.di.component.AppComponent;
import com.dpconde.taskexecutor.di.module.DataProviderModule;
import com.dpconde.taskexecutor.di.module.view.ChecklistListModule;
import com.dpconde.taskexecutor.di.module.view.tasks.MisuseTaskModule;
import com.dpconde.taskexecutor.mvp.view.task.misuse.MisuseActivity;
import com.dpconde.taskexecutor.mvp.view.task.misuse.MisusePresenter;

import javax.inject.Singleton;
import dagger.Component;
import dagger.Module;

/**
 * Created by dpconde on 28/9/18.
 */

@Singleton
@Component(modules = {DataProviderModule.class, MisuseTaskModule.class}, dependencies = {AppComponent.class})
public interface MisuseTaskComponent {

    void inject(MisuseActivity misuseActivity);
    MisusePresenter getMainPresenter();
}
