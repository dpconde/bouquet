package com.dpconde.taskexecutor.mvp.view;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.dpconde.taskexecutor.R;
import com.dpconde.taskexecutor.UserListApplication;
import com.dpconde.taskexecutor.di.component.AppComponent;
import com.dpconde.taskexecutor.di.component.DaggerLoginComponent;
import com.dpconde.taskexecutor.di.module.ContextModule;
import com.dpconde.taskexecutor.di.module.view.LoginModule;
import com.dpconde.taskexecutor.mvp.view.login.LoginPresenter;

import javax.inject.Inject;

public class GeneralActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    public void showErrorMessage(){}

}
