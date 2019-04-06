package com.dpconde.bouquet.di.component;

import com.dpconde.bouquet.di.module.DataProviderModule;
import com.dpconde.bouquet.di.module.view.ChecklistListModule;
import com.dpconde.bouquet.di.module.view.LoginModule;
import com.dpconde.bouquet.mvp.view.login.LoginActivity;
import com.dpconde.bouquet.mvp.view.login.LoginPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by dpconde on 28/9/18.
 */

@Singleton
@Component(modules = {LoginModule.class, DataProviderModule.class, ChecklistListModule.class}, dependencies = {AppComponent.class})
public interface LoginComponent {

    void inject(LoginActivity loginActivity);
    LoginPresenter getMainPresenter();
}
