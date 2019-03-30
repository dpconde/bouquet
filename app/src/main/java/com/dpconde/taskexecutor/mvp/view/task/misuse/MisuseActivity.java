package com.dpconde.taskexecutor.mvp.view.task.misuse;

import android.os.Bundle;
import android.view.View;

import com.dpconde.taskexecutor.R;
import com.dpconde.taskexecutor.UserListApplication;
import com.dpconde.taskexecutor.di.component.AppComponent;
import com.dpconde.taskexecutor.di.component.task.DaggerMisuseTaskComponent;
import com.dpconde.taskexecutor.di.module.ContextModule;
import com.dpconde.taskexecutor.di.module.view.tasks.MisuseTaskModule;
import com.dpconde.taskexecutor.mvp.view.GeneralActivity;


import javax.inject.Inject;

/**
 * Created by dpconde on 30/03/2019.
 */

public class MisuseActivity extends GeneralActivity implements MisusePresenter.View, View.OnClickListener {

    @Inject
    MisusePresenter presenter;

    // View components
    //TODO


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
        DaggerMisuseTaskComponent.builder()
                .contextModule(new ContextModule(this))
                .appComponent(appComponent)
                .misuseTaskModule(new MisuseTaskModule(this))
                .build()
                .inject(this);
    }


    /**
     * Instantiate view components
     */
    private void initViews() {

    }


    @Override
    public void onClick(View view) {

    }


}
