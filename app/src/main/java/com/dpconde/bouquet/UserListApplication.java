package com.dpconde.bouquet;

import android.app.Application;

import com.dpconde.bouquet.di.component.AppComponent;
import com.dpconde.bouquet.di.component.DaggerAppComponent;

/**
 * Created by dpconde on 28/9/18.
 */

public class UserListApplication extends Application {

    private AppComponent appComponent;
    private static UserListApplication app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        initializeInjector();
    }

    public static UserListApplication getApp() {
        return app;
    }

    private void initializeInjector() {
        appComponent = DaggerAppComponent
                .builder()
                .build();
    }

    public AppComponent component() {
        return appComponent;
    }
}
