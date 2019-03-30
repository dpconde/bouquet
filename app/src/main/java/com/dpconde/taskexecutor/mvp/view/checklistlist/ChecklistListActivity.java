package com.dpconde.taskexecutor.mvp.view.checklistlist;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.dpconde.taskexecutor.R;
import com.dpconde.taskexecutor.UserListApplication;
import com.dpconde.taskexecutor.di.component.AppComponent;
import com.dpconde.taskexecutor.di.component.DaggerChecklistListComponent;
import com.dpconde.taskexecutor.di.module.ContextModule;
import com.dpconde.taskexecutor.di.module.view.ChecklistListModule;
import com.dpconde.taskexecutor.mvp.data.model.Checklist;
import com.dpconde.taskexecutor.mvp.view.GeneralActivity;
import com.dpconde.taskexecutor.mvp.view.checklistlist.qrreader.ChecklistListQRReaderActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ChecklistListActivity extends GeneralActivity implements ChecklistListPresenter.View, View.OnClickListener {

    private final static int QR_REQUEST_CODE = 1;

    @Inject
    ChecklistListPresenter presenter;

    // View components
    RecyclerView recyclerView;
    RelativeLayout progressBarLayout;
    FloatingActionButton addChecklistButton;

    ChecklistListAdapter adapter;
    List<Checklist> checklistList = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setupComponent(UserListApplication.getApp().component());
        setContentView(R.layout.activity_checklist_list);
        super.onCreate(savedInstanceState);
        initViews();
        setupRecyclerView();
        requestPermissions();

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
        //Add drawer
        addDrawer();

        //Setup view components
        recyclerView = findViewById(R.id.recyclerView);
        progressBarLayout = findViewById(R.id.progressBarLayout);
        addChecklistButton = findViewById(R.id.addChecklistButton);
        addChecklistButton.setOnClickListener(this);

    }


    /**
     * Setup Recycler View component
     */
    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ChecklistListAdapter(presenter, checklistList);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void refreshChecklistList(List<Checklist> checklistList) {
        this.checklistList.clear();
        this.checklistList.addAll(checklistList);
        adapter.notifyDataSetChanged();
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addChecklistButton:

                Toast.makeText(this, "Add checklist", Toast.LENGTH_LONG).show();
                Intent i = new Intent(this, ChecklistListQRReaderActivity.class);
                startActivityForResult(i, QR_REQUEST_CODE);
                break;
            default:
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == QR_REQUEST_CODE) {
            if(resultCode == Activity.RESULT_OK){
                String result = data.getStringExtra("result");

               // presenter.readChecklist(Long.parseLong(result));
                presenter.readChecklist(1L);

                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Closing Activity")
                .setMessage("Are you sure you want to close this activity?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }


    private void requestPermissions(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[] {Manifest.permission.CAMERA}, 1);
            }
        }
    }
}
