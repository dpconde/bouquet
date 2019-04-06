package com.dpconde.bouquet.di.component.task;

import com.dpconde.bouquet.di.component.AppComponent;
import com.dpconde.bouquet.di.module.DataProviderModule;
import com.dpconde.bouquet.di.module.view.tasks.MisuseTaskModule;
import com.dpconde.bouquet.mvp.view.task.misuse.MisuseActivity;
import com.dpconde.bouquet.mvp.view.task.misuse.MisusePresenter;

import javax.inject.Singleton;
import dagger.Component;

/**
 * Created by dpconde on 28/9/18.
 */

@Singleton
@Component(modules = {DataProviderModule.class, MisuseTaskModule.class}, dependencies = {AppComponent.class})
public interface MisuseTaskComponent {

    void inject(MisuseActivity misuseActivity);
    MisusePresenter getMainPresenter();
}
