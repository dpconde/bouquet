package com.dpconde.taskexecutor.mvp.view.tasklist.dialogs;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.dpconde.taskexecutor.R;
import com.dpconde.taskexecutor.mvp.view.tasklist.TaskListPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.appcompat.app.AlertDialog;

/**
 * Created by dpconde on 02/03/2019.
 */

public class TaskListFilterDialog extends DialogFragment implements CompoundButton.OnCheckedChangeListener, AdapterView.OnItemSelectedListener {


    private final String STATUS_FILTER_KEY = "STATUS_FILTER_KEY";
    private final String SYNC_FILTER_KEY = "SYNC_FILTER_KEY";
    private final String TYPE_FILTER_KEY = "TYPE_FILTER_KEY";
    private Map<String, String> currentFilters;

    private RadioButton statusAll, statusDone, statusUndone, syncAll, syncSync, syncNotSync;
    private Spinner types;
    private List<String> testTypes;

    private Context context;
    private TaskListPresenter presenter;

    public static TaskListFilterDialog newInstance(Context context, Map<String, String> currentFilters,
                                                   List<String> testTypes, TaskListPresenter presenter) {

        TaskListFilterDialog dialog = new TaskListFilterDialog();
        dialog.currentFilters = currentFilters;
        dialog.testTypes = testTypes;
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
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, testTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        types.setAdapter(adapter);
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

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {

        switch(compoundButton.getId()) {
            case R.id.radio_status_all:
                if (checked){
                    currentFilters.put(STATUS_FILTER_KEY, "all");
                }
                break;

            case R.id.radio_status_done:
                if (checked){
                    currentFilters.put(STATUS_FILTER_KEY, "done");
                }
                break;

            case R.id.radio_status_undone:
                if (checked){
                    currentFilters.put(STATUS_FILTER_KEY, "undone");
                }
                break;

            case R.id.radio_sync_all:
                if (checked){
                    currentFilters.put(SYNC_FILTER_KEY, "all");
                }
                break;

            case R.id.radio_status_sync:
                if (checked){
                    currentFilters.put(SYNC_FILTER_KEY, "sync");
                }
                break;

            case R.id.radio_status_not_sync:
                if (checked){
                    currentFilters.put(SYNC_FILTER_KEY, "notsync");
                }
                break;

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}


