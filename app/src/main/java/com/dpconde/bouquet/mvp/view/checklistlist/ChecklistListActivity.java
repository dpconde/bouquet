package com.dpconde.bouquet.mvp.view.checklistlist;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.dpconde.bouquet.R;
import com.dpconde.bouquet.UserListApplication;
import com.dpconde.bouquet.di.component.AppComponent;
import com.dpconde.bouquet.di.component.DaggerChecklistListComponent;
import com.dpconde.bouquet.di.module.ContextModule;
import com.dpconde.bouquet.di.module.view.ChecklistListModule;
import com.dpconde.bouquet.mvp.data.model.Checklist;
import com.dpconde.bouquet.mvp.view.GeneralActivity;
import com.dpconde.bouquet.mvp.view.checklistlist.qrreader.ChecklistListQRReaderActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ChecklistListActivity extends GeneralActivity implements ChecklistListPresenter.View, View.OnClickListener, MenuItem.OnMenuItemClickListener {

    private final static int QR_REQUEST_CODE_ADD = 1;
    private final static int QR_REQUEST_CODE_SEARCH = 2;

    @Inject
    ChecklistListPresenter presenter;

    // View components
    RecyclerView recyclerView;
    RelativeLayout progressBarLayout;
    FloatingActionButton addChecklistButton;

    //Layouts
    LinearLayout availableChecklistsLayout;
    LinearLayout noAvailableChecklistLayout;


    ChecklistListAdapter adapter;
    List<Checklist> checklistList = new ArrayList<>();
    List<Checklist> checklistListFull = new ArrayList<>();




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

        //Layouts
        availableChecklistsLayout = findViewById(R.id.layout_checklists_available);
        noAvailableChecklistLayout = findViewById(R.id.layout_no_checklists_available);

        //Toolbar
        setSupportActionBar(toolbar);

    }


    /**
     * Setup Recycler View component
     */
    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ChecklistListAdapter(presenter, checklistList, checklistListFull, this);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void refreshChecklistList(List<Checklist> checklistList) {

        if(checklistList.isEmpty()){

            //Refresh layout
            availableChecklistsLayout.setVisibility(View.GONE);
            noAvailableChecklistLayout.setVisibility(View.VISIBLE);

        }else{

            //Refresh layout
            availableChecklistsLayout.setVisibility(View.VISIBLE);
            noAvailableChecklistLayout.setVisibility(View.GONE);

            this.checklistList.clear();
            this.checklistList.addAll(checklistList);
            this.checklistListFull.clear();
            this.checklistListFull.addAll(checklistList);
            adapter.notifyDataSetChanged();
        }
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

                Intent i = new Intent(this, ChecklistListQRReaderActivity.class);
                startActivityForResult(i, QR_REQUEST_CODE_ADD);

                break;
            default:
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == QR_REQUEST_CODE_ADD) {
            if(resultCode == Activity.RESULT_OK){
                String result = data.getStringExtra("result");

               // presenter.readChecklist(Long.parseLong(result));
                presenter.readChecklist(1L);

                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
            }

        } else if(requestCode == QR_REQUEST_CODE_SEARCH){
            if(resultCode == Activity.RESULT_OK){
                String result = data.getStringExtra("result");



                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity_checklist_list, menu);

        //Search menu item
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String searchPattern) {
                adapter.getFilter().filter(searchPattern);
                return false;
            }
        });

        //QR code checklist opener
        MenuItem qrCodeItem = menu.findItem(R.id.qr_search);
        qrCodeItem.setOnMenuItemClickListener(this);

        return true;
    }



    private void requestPermissions(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[] {Manifest.permission.CAMERA}, 1);
            }
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {

        if(menuItem.getItemId() == R.id.qr_search){
            Intent i = new Intent(this, ChecklistListQRReaderActivity.class);
            startActivityForResult(i, QR_REQUEST_CODE_SEARCH);
        }

        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            openCloseDrawer();
        }
        return true;
    }
}
