package com.dpconde.bouquet.mvp.view.login;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import com.dpconde.bouquet.R;
import com.dpconde.bouquet.UserListApplication;
import com.dpconde.bouquet.di.component.AppComponent;
import com.dpconde.bouquet.di.component.DaggerLoginComponent;
import com.dpconde.bouquet.di.module.ContextModule;
import com.dpconde.bouquet.di.module.view.LoginModule;
import com.dpconde.bouquet.mvp.view.GeneralActivity;
import com.google.android.material.textfield.TextInputEditText;

import javax.inject.Inject;

public class LoginActivity extends GeneralActivity implements LoginPresenter.View, View.OnClickListener {

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
        loginButton.setOnClickListener(this);

        //TODO delete
        userCodeField.setText("AT015421");
        passwordField.setText("DP344739id");
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.loginButton:

                //Get login values
                String userCode = userCodeField.getText().toString();
                String password = passwordField.getText().toString();

                presenter.doOnlineLogin(userCode, password);

                break;
            default:
                break;
        }
    }


}
