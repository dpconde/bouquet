package com.dpconde.bouquet.mvp.view.tasklist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dpconde.bouquet.R;
import com.dpconde.bouquet.mvp.Constants;
import com.dpconde.bouquet.mvp.data.model.Task;
import com.dpconde.bouquet.mvp.view.tasklist.types.ContainerViewHolder;
import com.dpconde.bouquet.mvp.view.tasklist.types.GeneralViewHolder;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


public class TaskListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private TaskListPresenter presenter;
    private List<Task> taskList;
    private Context context;
    private boolean filtersApplied = false;

    public TaskListAdapter(TaskListPresenter presenter, List<Task> taskList, Context context) {
        this.taskList = taskList;
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View itemView;

        switch (viewType){
            case Constants.TASK_TYPE_CONTAINER_VIEW_ID:
                itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.task_list_item_container, viewGroup, false);
                return new ContainerViewHolder(itemView, context);

            default:
                itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.task_list_item, viewGroup, false);
                return new GeneralViewHolder(itemView, context);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        //Get selected user from list
        final Task task = taskList.get(position);

        if(task != null) {

            if(task.getTaskType().getId() == Constants.TASK_TYPE_CONTAINER_ID){
                ((ContainerViewHolder) holder).bindData(task, filtersApplied);
            }else{
                ((GeneralViewHolder) holder).bindData(task, filtersApplied);
            }


            //Add click event listener
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presenter.onItemClicked(task);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    @Override
    public int getItemViewType(int position) {

        if(taskList.get(position).getTaskType().getId() == Constants.TASK_TYPE_CONTAINER_ID){
            return Constants.TASK_TYPE_CONTAINER_VIEW_ID;
        }else if(taskList.get(position).getTaskType().getId() == Constants.TASK_TYPE_GENERAL_ID){
            return Constants.TASK_TYPE_GENERAL_VIEW_ID;
        }else if(taskList.get(position).getTaskType().getId() == Constants.TASK_TYPE_CHECKLIST_ID){
            return Constants.TASK_TYPE_CHECKLIST_VIEW_ID;
        }else if(taskList.get(position).getTaskType().getId() == Constants.TASK_TYPE_MISUSE_ID){
            return Constants.TASK_TYPE_MISUSE_VIEW_ID;
        } else{
            return -1;
        }

    }

    public void setFiltersApplied(boolean filtersApplied) {
        this.filtersApplied = filtersApplied;
    }
}
