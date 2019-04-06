package com.dpconde.bouquet.mvp.view.task.misuse;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.dpconde.bouquet.R;
import com.dpconde.bouquet.UserListApplication;
import com.dpconde.bouquet.di.component.AppComponent;
import com.dpconde.bouquet.di.component.task.DaggerMisuseTaskComponent;
import com.dpconde.bouquet.di.module.ContextModule;
import com.dpconde.bouquet.di.module.view.tasks.MisuseTaskModule;
import com.dpconde.bouquet.mvp.view.GeneralActivity;


import javax.inject.Inject;

/**
 * Created by dpconde on 30/03/2019.
 */

public class MisuseActivity extends GeneralActivity implements MisusePresenter.View, View.OnClickListener {

    @Inject
    MisusePresenter presenter;

    // View components
    private

    //Menu
    Menu menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupComponent(UserListApplication.getApp().component());
        setContentView(R.layout.activity_task_misuse);
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

        //Toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    /**
     * Add menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_task_misuse, menu);
        this.menu = menu;
        return true;
    }


    @Override
    public void onClick(View view) {

    }


}
