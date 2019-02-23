package com.dpconde.taskexecutor.mvp.view.checklistlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dpconde.taskexecutor.R;
import com.dpconde.taskexecutor.mvp.data.model.Checklist;
import com.dpconde.taskexecutor.mvp.data.model.User;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dpconde on 28/9/18.
 */

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.CategoryViewHolder> {

    private ChecklistListPresenter presenter;
    private List<Checklist> checklistList;

    public UsersAdapter(ChecklistListPresenter presenter, List<Checklist> checklistList) {
        this.checklistList = checklistList;
        this.presenter = presenter;
    }

    @Override
    public UsersAdapter.CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item, parent, false);
        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UsersAdapter.CategoryViewHolder holder, final int position) {

        //Get selected user from list
        final Checklist checklist = checklistList.get(position);

        //Bind data
       // holder.username.setText(user.getName().toString());
       // holder.userLocation.setText(user.getLocation().toString());
       // Picasso.get().load(user.getPicture().getMedium()).into(holder.imageView);

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

        ImageView imageView;
        TextView username;
        TextView userLocation;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.userImage);
            username = itemView.findViewById(R.id.userName);
            userLocation = itemView.findViewById(R.id.userLocation);
        }
    }
}
