package com.dpconde.taskexecutor.mvp.view.tasklist;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dpconde.taskexecutor.R;
import com.dpconde.taskexecutor.UserListApplication;
import com.dpconde.taskexecutor.di.component.AppComponent;
import com.dpconde.taskexecutor.di.component.DaggerTaskListComponent;
import com.dpconde.taskexecutor.di.module.ContextModule;
import com.dpconde.taskexecutor.di.module.view.TaskListModule;
import com.dpconde.taskexecutor.mvp.data.model.Checklist;
import com.dpconde.taskexecutor.mvp.data.model.User;
import com.dpconde.taskexecutor.mvp.view.GeneralActivity;


import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TaskListActivity extends GeneralActivity implements TaskListPresenter.View, View.OnClickListener {

    @Inject
    TaskListPresenter presenter;

    //Views
    private RecyclerView recyclerView;
    private TaskListAdapter adapter;

    //Current checklist
    private Checklist currentChecklist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setupComponent(UserListApplication.getApp().component());
        setContentView(R.layout.activity_task_list);
        super.onCreate(savedInstanceState);

        //Get user from DB
        Long currentUserId = getIntent().getLongExtra("checklistIntent", 0L);
        currentChecklist = presenter.getChecklistById(currentUserId);

        initViews();
        loadViews(presenter.getCurrentUser());
    }


    /**
     * Inject dependencies method
     * @param appComponent
     */
    protected void setupComponent(AppComponent appComponent) {

        DaggerTaskListComponent.builder()
                .appComponent(appComponent)
                .taskListModule(new TaskListModule(this))
                .contextModule(new ContextModule(this))
                .build()
                .inject(this);
    }

    /**
     * Instantiate view components
     */
    private void initViews() {

        recyclerView = findViewById(R.id.task_list);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);

        //Toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    /**
     * Load views content for the given user
     * @param user
     */
    private void loadViews(User user){
        adapter = new TaskListAdapter(presenter, currentChecklist.getTasks(), this);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onClick(View view) {

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void finishView() {
        finish();
    }
}
