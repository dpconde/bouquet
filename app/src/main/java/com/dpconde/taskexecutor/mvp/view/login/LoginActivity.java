package com.dpconde.taskexecutor.mvp.view.login;

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

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity implements LoginPresenter.View, View.OnClickListener {

    @Inject
    LoginPresenter presenter;

    // View components
    private TextInputEditText userCodeField;
    private TextInputEditText passwordField;
    private Button loginButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupComponent(UserListApplication.getApp().component());
        setContentView(R.layout.activity_login);
        initViews();
    }

    @Override
    protected void onResume(){
        super.onResume();
    }


    /**
     * Inject dependencies method
     * @param appComponent
     */
    protected void setupComponent(AppComponent appComponent) {
        DaggerLoginComponent.builder()
                .contextModule(new ContextModule(this))
                .appComponent(appComponent)
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
    }


    /**
     * Instantiate view components
     */
    private void initViews() {
        userCodeField = findViewById(R.id.loginUserCode);
        passwordField = findViewById(R.id.loginPassword);
        loginButton = findViewById(R.id.loginButton);
    }


    @Override
    public void showProgress() {

    }


    @Override
    public void hideProgress() {

    }



    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.loginButton:

                //Get login values
                String userCode = userCodeField.getText().toString();
                String password = passwordField.getText().toString();

                presenter.doLogin(userCode, password);

                break;
            default:
                break;
        }
    }
}