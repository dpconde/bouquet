package com.dpconde.taskexecutor.mvp.view.tasklist.types;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dpconde.taskexecutor.R;
import com.dpconde.taskexecutor.mvp.data.model.Task;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by dpconde on 28/02/2019.
 */

public class GeneralViewHolder extends RecyclerView.ViewHolder {

    private final static int LEVEL_WIDTH = 40; //in dp

    private RelativeLayout statusLayout;
    private LinearLayout levelsContainer;
    private TextView description;
    private TextView taskType;
    private ImageView syncStatus;

    private Context context;



    public GeneralViewHolder(final View itemView, Context context) {
        super(itemView);

        this.context = context;

        statusLayout = itemView.findViewById(R.id.status_layout);
        description = itemView.findViewById(R.id.task_description);
        taskType = itemView.findViewById(R.id.task_type);
        syncStatus = itemView.findViewById(R.id.sync_status);
        levelsContainer = itemView.findViewById(R.id.level_container);


    }

    public void bindData(Task task, boolean filtersApplied){
        description.setText(task.getDescription());
        taskType.setText(String.valueOf(task.getTaskType().getDescription()));

        final float scale = context.getResources().getDisplayMetrics().density;
        int pixels = (int) (LEVEL_WIDTH * task.getDepth() * scale + 0.5f);

        ViewGroup.LayoutParams params = levelsContainer.getLayoutParams();

        // Changes the height and width to the specified *pixels*
        params.width = filtersApplied ? 0 : pixels;
        levelsContainer.setLayoutParams(params);


        //TODO cambiar el color dependiendo del estado de la tarea

        //TODO cambiar el icono del cloud dependiendo del estado de sincronizacion
    }


}