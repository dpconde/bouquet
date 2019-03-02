package com.dpconde.taskexecutor.mvp.view.tasklist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dpconde.taskexecutor.R;
import com.dpconde.taskexecutor.mvp.data.model.Task;
import com.dpconde.taskexecutor.mvp.view.tasklist.types.ContainerViewHolder;
import com.dpconde.taskexecutor.mvp.view.tasklist.types.GeneralViewHolder;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


public class TaskListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int CONTAINER_TASK = 2;


    private TaskListPresenter presenter;
    private List<Task> taskList;
    private Context context;

    public TaskListAdapter(TaskListPresenter presenter, List<Task> taskList, Context context) {
        this.taskList = taskList;
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View itemView;
        switch (viewType){
            case CONTAINER_TASK:
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
            switch (task.getType()) {
                case CONTAINER_TASK:
                    ((ContainerViewHolder) holder).bindData(task);
                    break;

                default:
                    ((GeneralViewHolder) holder).bindData(task);
                    break;
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
        return taskList.get(position).getType();
    }
}
