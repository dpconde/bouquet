package com.dpconde.taskexecutor.mvp.view.task.general;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dpconde.taskexecutor.R;
import com.dpconde.taskexecutor.UserListApplication;
import com.dpconde.taskexecutor.di.component.AppComponent;
import com.dpconde.taskexecutor.di.component.DaggerLoginComponent;
import com.dpconde.taskexecutor.di.module.ContextModule;
import com.dpconde.taskexecutor.di.module.view.LoginModule;
import com.dpconde.taskexecutor.mvp.view.GeneralActivity;
import com.dpconde.taskexecutor.mvp.view.login.LoginPresenter;
import com.google.android.material.textfield.TextInputEditText;

import javax.inject.Inject;


public class GeneralTaskActivity extends GeneralActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setupComponent(UserListApplication.getApp().component());
        setContentView(R.layout.activity_login);
        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    /**
     * Inject dependencies method
     * @param appComponent
     */
   /* protected void setupComponent(AppComponent appComponent) {
        DaggerLoginComponent.builder()
                .contextModule(new ContextModule(this))
                .appComponent(appComponent)
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
    }*/


    /**
     * Instantiate view components
     */
    private void initViews() {

    }
}




