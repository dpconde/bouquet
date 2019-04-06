package com.dpconde.bouquet.di.module.view;

import android.content.Context;

import com.dpconde.bouquet.di.module.ContextModule;
import com.dpconde.bouquet.di.module.UserModule;
import com.dpconde.bouquet.mvp.data.api.UserManager;
import com.dpconde.bouquet.mvp.view.login.LoginPresenter;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dpconde on 29/9/18.
 */

@Module(includes = {UserModule.class, ContextModule.class})
public class LoginModule {

    private LoginPresenter.View view;

    public LoginModule(LoginPresenter.View view) {
        this.view = view;
    }

    @Provides
    public LoginPresenter.View provideView() {
        return view;
    }

    @Provides
    public LoginPresenter providePresenter(LoginPresenter.View loginView,
                                           Context context,
                                           @Named("api") UserManager userManagerAPI,
                                           @Named("db") UserManager userManagerDB) {
        return new LoginPresenter(loginView, context, userManagerAPI, userManagerDB);
    }

}
