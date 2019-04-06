package com.dpconde.bouquet.di.component;

import com.dpconde.bouquet.UserListApplication;
import com.dpconde.bouquet.di.module.AppModule;

import dagger.Component;

/**
 * Created by dpconde on 29/9/18.
 */

@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(UserListApplication categoryApplication);
}
