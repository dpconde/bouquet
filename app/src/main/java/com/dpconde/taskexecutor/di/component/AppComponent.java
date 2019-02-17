package com.dpconde.taskexecutor.di.component;

import com.dpconde.taskexecutor.UserListApplication;
import com.dpconde.taskexecutor.di.module.AppModule;

import dagger.Component;

/**
 * Created by dpconde on 29/9/18.
 */

@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(UserListApplication categoryApplication);
}
