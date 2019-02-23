package com.dpconde.taskexecutor.di.module.view;

import android.content.Context;

import com.dpconde.taskexecutor.di.module.ContextModule;
import com.dpconde.taskexecutor.di.module.TaskModule;
import com.dpconde.taskexecutor.di.module.UserModule;
import com.dpconde.taskexecutor.mvp.data.api.UserManager;
import com.dpconde.taskexecutor.mvp.view.login.LoginPresenter;

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
                                                   Context context, @Named("db") UserManager userManagerDB) {
        return new LoginPresenter(loginView, context, userManagerDB);
    }

}
