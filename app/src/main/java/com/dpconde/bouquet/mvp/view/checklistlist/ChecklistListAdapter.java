package com.dpconde.bouquet.mvp.view.checklistlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dpconde.bouquet.R;
import com.dpconde.bouquet.mvp.data.model.Checklist;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by dpconde on 28/9/18.
 */

public class ChecklistListAdapter extends RecyclerView.Adapter<ChecklistListAdapter.CategoryViewHolder> implements Filterable{

    private ChecklistListPresenter presenter;
    private List<Checklist> checklistList;
    private List<Checklist> checklistListFull;
    private Context context;

    public ChecklistListAdapter(ChecklistListPresenter presenter, List<Checklist> checklistList, List<Checklist> checklistListFull, Context context) {
        this.checklistList = checklistList;
        this.presenter = presenter;
        this.checklistListFull = checklistListFull;
        this.context = context;
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

        holder.samplesList.removeAllViews();
        for(String sampleText: checklist.getSamples().toString().split("#")){
            TextView sample = new TextView(context);
            sample.setText(sampleText);
            holder.samplesList.addView(sample);
        }

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

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Checklist> filteredList = new ArrayList<>();

            if(charSequence == null || charSequence.length() == 0){

                filteredList.addAll(checklistListFull);

            }else{

                String filterPattern = charSequence.toString().toLowerCase().trim();

                for(Checklist checklist: checklistListFull){
                    if(checklist.getId().toString().contains(filterPattern)){
                        filteredList.add(checklist);
                    }else if(checklist.getDescription().contains(filterPattern)){
                        filteredList.add(checklist);
                    }else if(checklist.getCustomer().toLowerCase().contains(filterPattern)){
                        filteredList.add(checklist);
                    }else if(checklist.getProjectName().toLowerCase().contains(filterPattern)){
                        filteredList.add(checklist);
                    }else if(checklist.getSamples().toLowerCase().contains(filterPattern)){
                        filteredList.add(checklist);
                    }else if(checklist.getWorkingOrder().toLowerCase().contains(filterPattern)){
                        filteredList.add(checklist);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            checklistList.clear();
            checklistList.addAll((List<Checklist>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {

        TextView id;
        TextView description;
        TextView project;
        TextView workingOrder;
        LinearLayout samplesList;
        TextView numTests;
        TextView status;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            description = itemView.findViewById(R.id.description);
            project = itemView.findViewById(R.id.project);
            workingOrder = itemView.findViewById(R.id.workingOrder);
            samplesList = itemView.findViewById(R.id.samples);
            numTests = itemView.findViewById(R.id.numTests);
            status = itemView.findViewById(R.id.status);
        }
    }
}
