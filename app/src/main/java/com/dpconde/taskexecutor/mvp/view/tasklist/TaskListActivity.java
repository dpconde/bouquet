package com.dpconde.taskexecutor.mvp.view.tasklist;

import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.dpconde.taskexecutor.R;
import com.dpconde.taskexecutor.UserListApplication;
import com.dpconde.taskexecutor.di.component.AppComponent;
import com.dpconde.taskexecutor.di.component.DaggerTaskListComponent;
import com.dpconde.taskexecutor.di.module.ContextModule;
import com.dpconde.taskexecutor.di.module.view.TaskListModule;
import com.dpconde.taskexecutor.mvp.data.model.Checklist;
import com.dpconde.taskexecutor.mvp.data.model.Task;
import com.dpconde.taskexecutor.mvp.data.model.TaskType;
import com.dpconde.taskexecutor.mvp.view.GeneralActivity;
import com.dpconde.taskexecutor.mvp.view.tasklist.dialogs.TaskListFilterDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import androidx.appcompat.view.menu.MenuBuilder;
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
    private List<Task> taskList = new ArrayList<>();

    //Filtering dialog
    TaskListFilterDialog filteringDialog;

    //Menu
    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setupComponent(UserListApplication.getApp().component());
        setContentView(R.layout.activity_task_list);
        super.onCreate(savedInstanceState);

        //Get checklist from DB
        Long currentUserId = getIntent().getLongExtra("checklistIntent", 0L);
        presenter.setCurrentChecklistId(currentUserId);
        currentChecklist = presenter.getChecklistById(currentUserId);
        taskList.addAll(presenter.getFirstLevelTasks(currentChecklist.getId()));

        initViews();
        loadViews();
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
     */
    private void loadViews(){
        adapter = new TaskListAdapter(presenter, taskList, this);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void refreshList(List<Task> taskList, boolean withFilters){
        adapter.setFiltersApplied(withFilters);
        this.taskList.clear();
        this.taskList.addAll(taskList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void changeFilteringIcon(boolean filtering) {
        if(filtering){
            ((MenuBuilder) menu).getActionItems().get(0).setIcon(R.drawable.ic_filter_active);
        }else{
            ((MenuBuilder) menu).getActionItems().get(0).setIcon(R.drawable.ic_filter_inactive);
        }
    }

    /**
     * Add menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_task_list, menu);
        this.menu = menu;
        return true;
    }

    /**
     * Manage menu items selection
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.task_filter_menu_option:

                List<TaskType> types = presenter.getTaskTypesByChecklistId();

                //Add all types option
                TaskType allTypes = new TaskType();
                allTypes.setId(-1);
                allTypes.setDescription("All");
                types.add(0, allTypes);

                FragmentManager ft = getFragmentManager();
                if(filteringDialog == null){
                    filteringDialog = TaskListFilterDialog.newInstance(this,
                            new HashMap<String, Object>(), types, presenter);
                }
                filteringDialog.show(ft, "dialog");

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
