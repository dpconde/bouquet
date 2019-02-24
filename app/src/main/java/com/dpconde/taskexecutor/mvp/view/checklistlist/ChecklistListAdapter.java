package com.dpconde.taskexecutor.mvp.view.checklistlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dpconde.taskexecutor.R;
import com.dpconde.taskexecutor.mvp.data.model.Checklist;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by dpconde on 28/9/18.
 */

public class ChecklistListAdapter extends RecyclerView.Adapter<ChecklistListAdapter.CategoryViewHolder> {

    private ChecklistListPresenter presenter;
    private List<Checklist> checklistList;

    public ChecklistListAdapter(ChecklistListPresenter presenter, List<Checklist> checklistList) {
        this.checklistList = checklistList;
        this.presenter = presenter;
    }

    @Override
    public ChecklistListAdapter.CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.checklist_list_item, parent, false);
        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ChecklistListAdapter.CategoryViewHolder holder, final int position) {

        //Get selected user from list
        final Checklist checklist = checklistList.get(position);

        //Bind data
        holder.id.setText(checklist.getId().toString());
        holder.description.setText(checklist.getDescription().toString());
        holder.project.setText(checklist.getProjectName().toString());
        holder.workingOrder.setText(checklist.getWorkingOrder().toString());
        holder.samples.setText(checklist.getSamples().toString());
        holder.numTests.setText(9+"");
        holder.status.setText("Status");


        //Add click event listener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onItemClicked(checklist);
            }
        });
    }

    @Override
    public int getItemCount() {
        return checklistList.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {

        TextView id;
        TextView description;
        TextView project;
        TextView workingOrder;
        TextView samples;
        TextView numTests;
        TextView status;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            description = itemView.findViewById(R.id.description);
            project = itemView.findViewById(R.id.project);
            workingOrder = itemView.findViewById(R.id.workingOrder);
            samples = itemView.findViewById(R.id.samples);
            numTests = itemView.findViewById(R.id.numTests);
            status = itemView.findViewById(R.id.status);
        }
    }
}
