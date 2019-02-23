package com.dpconde.taskexecutor.mvp.view.checklistlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.dpconde.taskexecutor.R;
import com.dpconde.taskexecutor.UserListApplication;
import com.dpconde.taskexecutor.di.component.AppComponent;
import com.dpconde.taskexecutor.di.component.DaggerChecklistListComponent;
import com.dpconde.taskexecutor.di.module.ContextModule;
import com.dpconde.taskexecutor.di.module.view.ChecklistListModule;
import com.dpconde.taskexecutor.mvp.data.model.Checklist;
import com.dpconde.taskexecutor.mvp.data.model.User;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ChecklistListActivity extends AppCompatActivity implements ChecklistListPresenter.View, View.OnClickListener {

    @Inject
    ChecklistListPresenter presenter;

    // View components
    RecyclerView recyclerView;
    Button prevButton;
    Button nextButton;
    RelativeLayout progressBarLayout;

    UsersAdapter adapter;
    List<Checklist> userList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupComponent(UserListApplication.getApp().component());
        setContentView(R.layout.activity_user_list);
        initViews();
        setupRecyclerView();
    }

    @Override
    protected void onResume(){
        super.onResume();
        presenter.loadData();
    }


    /**
     * Inject dependencies method
     * @param appComponent
     */
    protected void setupComponent(AppComponent appComponent) {
        DaggerChecklistListComponent.builder()
                .contextModule(new ContextModule(this))
                .appComponent(appComponent)
                .checklistListModule(new ChecklistListModule(this))
                .build()
                .inject(this);
    }


    /**
     * Instantiate view components
     */
    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        nextButton = findViewById(R.id.nextPageButton);
        nextButton.setOnClickListener(this);
        prevButton = findViewById(R.id.prevPageButton);
        prevButton.setOnClickListener(this);
        progressBarLayout = findViewById(R.id.progressBarLayout);
    }


    /**
     * Setup Recycler View component
     */
    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new UsersAdapter(presenter, userList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showProgress() {
        progressBarLayout.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
    }


    @Override
    public void hideProgress() {
        progressBarLayout.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }


    @Override
    public void refreshChecklistList(List<Checklist> checklistList) {
        this.userList.clear();
        this.userList.addAll(checklistList);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onClick(View view) {

    }
}
