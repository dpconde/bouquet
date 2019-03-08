package com.dpconde.taskexecutor.mvp.view.tasklist;

import android.content.Context;

import com.dpconde.taskexecutor.mvp.data.api.TestDataManager;
import com.dpconde.taskexecutor.mvp.data.model.Checklist;
import com.dpconde.taskexecutor.mvp.data.model.Task;
import com.dpconde.taskexecutor.mvp.data.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dpconde on 28/9/18.
 */

public class TaskListPresenter {

    //Dependencies
    private View view;
    private TestDataManager userDataManager;
    private Context context;
    private long currentChecklistId;


    public TaskListPresenter(TaskListPresenter.View view, TestDataManager userDataManager, Context context) {
        this.view = view;
        this.userDataManager = userDataManager;
        this.context = context;
    }


    public void filterTasks(Map<String, String> filters){

        //TODO filter results

        view.refreshList(new ArrayList<Task>());
    }


    public void onItemClicked(Task task) {
    }

    public Checklist getChecklistById(Long checklistId) {
        Checklist checklist = userDataManager.loadChecklist(null, checklistId);

        return checklist;
    }


    public interface View {

        /**
         * Show message to user
         * @param message
         */
        void showMessage(String message);

        /**
         * Finish current activity
         */
        void finishView();

        /**
         * Refresh list
         * @param list
         */
        void refreshList(List<Task> list);
    }

}
