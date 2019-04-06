package com.dpconde.bouquet.mvp.view.tasklist.dialogs;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.dpconde.bouquet.R;
import com.dpconde.bouquet.mvp.Constants;
import com.dpconde.bouquet.mvp.data.model.TaskType;
import com.dpconde.bouquet.mvp.view.tasklist.TaskListPresenter;

import java.util.List;
import java.util.Map;

import androidx.appcompat.app.AlertDialog;

/**
 * Created by dpconde on 02/03/2019.
 */

public class TaskListFilterDialog extends DialogFragment implements CompoundButton.OnCheckedChangeListener, AdapterView.OnItemSelectedListener {

    private Map<String, Object> currentFilters;

    private RadioButton statusAll, statusDone, statusUndone, syncAll, syncSync, syncNotSync;
    private Spinner types;
    private List<TaskType> taskTypes;
    private TaskTypeSpinnerAdapter taskTypesAdapter;

    private Context context;
    private TaskListPresenter presenter;


    public static TaskListFilterDialog newInstance(Context context, Map<String, Object> currentFilters,
            List<TaskType> taskTypes, TaskListPresenter presenter) {

        TaskListFilterDialog dialog = new TaskListFilterDialog();
        dialog.taskTypes = taskTypes;
        dialog.currentFilters = currentFilters;
        dialog.context = context;
        dialog.presenter = presenter;

        return dialog;
    }




    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.activity_task_list_filter_dialog, null);

        statusAll = view.findViewById(R.id.radio_status_all);
        statusAll.setOnCheckedChangeListener(this);
        statusDone = view.findViewById(R.id.radio_status_done);
        statusDone.setOnCheckedChangeListener(this);
        statusUndone = view.findViewById(R.id.radio_status_undone);
        statusUndone.setOnCheckedChangeListener(this);

        syncAll = view.findViewById(R.id.radio_sync_all);
        syncAll.setOnCheckedChangeListener(this);
        syncSync = view.findViewById(R.id.radio_status_sync);
        syncSync.setOnCheckedChangeListener(this);
        syncNotSync = view.findViewById(R.id.radio_status_not_sync);
        syncNotSync.setOnCheckedChangeListener(this);

        types = view.findViewById(R.id.task_filter_types_spinner);
        taskTypesAdapter = new TaskTypeSpinnerAdapter(context, android.R.layout.simple_spinner_item, taskTypes);
        taskTypesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        types.setAdapter(taskTypesAdapter);
        types.setOnItemSelectedListener(this);

        builder.setView(view)
                .setTitle("Filter tasks")
                .setPositiveButton("Filter", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        presenter.filterTasks(currentFilters);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });


        return builder.create();
    }

    private void setCurrentFilters(Map<String, Object> currentFilters) {
        if(currentFilters.containsKey(Constants.STATUS_FILTER_KEY)){
            if(currentFilters.get(Constants.STATUS_FILTER_KEY).equals("all")){
                statusAll.setChecked(true);
            }else if(currentFilters.get(Constants.STATUS_FILTER_KEY).equals("done")){
                statusDone.setChecked(true);
            }else if(currentFilters.get(Constants.STATUS_FILTER_KEY).equals("undone")){
                statusUndone.setChecked(true);
            }

        }

        if(currentFilters.containsKey(Constants.SYNC_FILTER_KEY)){
            if(currentFilters.get(Constants.SYNC_FILTER_KEY).equals("all")){
                syncAll.setChecked(true);
            }else if(currentFilters.get(Constants.SYNC_FILTER_KEY).equals("sync")){
                syncSync.setChecked(true);
            }else if(currentFilters.get(Constants.SYNC_FILTER_KEY).equals("notsync")){
                syncNotSync.setChecked(true);
            }

        }

        if(currentFilters.containsKey(Constants.TYPE_FILTER_KEY)){
            TaskType taskType = (TaskType) currentFilters.get(Constants.TYPE_FILTER_KEY);
            if(taskType.getId() == -1L){
                types.setSelection(0);
            }else{
                types.setSelection(taskTypesAdapter.getPosition(taskType));
                currentFilters.put(Constants.TYPE_FILTER_KEY, taskType);
            }
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {

        switch(compoundButton.getId()) {
            case R.id.radio_status_all:
                if (checked){
                    currentFilters.put(Constants.STATUS_FILTER_KEY, "all");
                }
                break;

            case R.id.radio_status_done:
                if (checked){
                    currentFilters.put(Constants.STATUS_FILTER_KEY, "done");
                }
                break;

            case R.id.radio_status_undone:
                if (checked){
                    currentFilters.put(Constants.STATUS_FILTER_KEY, "undone");
                }
                break;

            case R.id.radio_sync_all:
                if (checked){
                    currentFilters.put(Constants.SYNC_FILTER_KEY, "all");
                }
                break;

            case R.id.radio_status_sync:
                if (checked){
                    currentFilters.put(Constants.SYNC_FILTER_KEY, "sync");
                }
                break;

            case R.id.radio_status_not_sync:
                if (checked){
                    currentFilters.put(Constants.SYNC_FILTER_KEY, "notsync");
                }
                break;

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        TaskType selectedTaskType = (TaskType) adapterView.getAdapter().getItem(i);
        currentFilters.put(Constants.TYPE_FILTER_KEY, selectedTaskType);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onStart(){
        super.onStart();
        this.setCurrentFilters(currentFilters);
    }

    @Override
    public void onDismiss(DialogInterface dialog){
        super.onDismiss(dialog);
        presenter.updateFilteringMenuIcon(currentFilters);
    }
}


