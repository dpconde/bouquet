package com.dpconde.bouquet.mvp.view.task.general;

import android.os.Bundle;

import com.dpconde.bouquet.R;
import com.dpconde.bouquet.mvp.view.GeneralActivity;


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




