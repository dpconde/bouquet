package com.dpconde.taskexecutor.mvp.data.api.jsonreader;

import android.content.Context;

import com.dpconde.taskexecutor.R;
import com.dpconde.taskexecutor.mvp.data.model.Checklist;
import com.dpconde.taskexecutor.mvp.data.model.tasks.ChecklistTask;
import com.dpconde.taskexecutor.mvp.data.model.tasks.ContainerTask;
import com.dpconde.taskexecutor.mvp.data.model.tasks.GeneralTask;
import com.dpconde.taskexecutor.mvp.data.model.tasks.PictureTask;
import com.dpconde.taskexecutor.mvp.view.checklistlist.ChecklistListCallback;
import com.dpconde.taskexecutor.mvp.data.api.TestDataManager;
import com.dpconde.taskexecutor.mvp.data.model.Task;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dpconde on 13/02/2019.
 */

public class TestDataManagerJson implements TestDataManager {

    private Context context;
    private Gson gson;


    public TestDataManagerJson (Context context, Gson gson){
        this.context = context;
        this.gson = gson;
    }


    @Override
    public void loadTasks(ChecklistListCallback checklistListCallback) {
        try {
            InputStream inputStream = context.getResources().openRawResource(R.raw.checklist);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, bytes.length);
            String json = new String(bytes);

            //Json to Properties
            JsonObject jsonObject = gson.fromJson( json, JsonObject.class);

            Checklist checklist = parseJsonChecklist(jsonObject);


        } catch (IOException e) {

        }
    }


    private Checklist parseJsonChecklist(JsonObject jsonChecklist){

        Checklist checklist = new Checklist();
        List<Task> tasks = new ArrayList<>();

        Long id = jsonChecklist.get("id").getAsLong();
        String description = jsonChecklist.get("description").getAsString();
        String projectName = jsonChecklist.get("projectName").getAsString();
        String workingOrder = jsonChecklist.get("workingOrder").getAsString();
        String customer = jsonChecklist.get("customer").getAsString();
        String samples = jsonChecklist.get("samples").getAsString();

        //Get Checklist tasks
        JsonArray jsonTasks = jsonChecklist.getAsJsonArray("tasks");
        for(JsonElement jsonElement: jsonTasks){
            JsonObject task = jsonElement.getAsJsonObject();
            int type = task.get("type").getAsInt();

            switch(type) {
                case 1: //General Task
                    tasks.add(parseGeneralTask(task, id));
                    break;
                case 2: //Container Task
                    tasks.add(parseContainerTask(task, id));
                    break;
                case 3: //Checklist Task
                    tasks.add(parseChecklistTask(task, id));
                    break;
                case 4: //Picture Task
                    tasks.add(parsePictureTask(task, id));
                    break;
                default:
            }
        }

        checklist.setId(id);
        checklist.setProjectName(projectName);
        checklist.setWorkingOrder(workingOrder);
        checklist.setDescription(description);
        checklist.setCustomer(customer);
        checklist.setSamples(samples);
        checklist.setTasks(tasks);

        return checklist;
    }

    private ChecklistTask parseChecklistTask(JsonObject jsonChecklistTask, long checklistId){
        return null;
    }

    private GeneralTask parseGeneralTask(JsonObject jsonGeneralTask, long checklistId){
        return null;
    }

    private PictureTask parsePictureTask(JsonObject jsonPictureTask, long checklistId){
        return null;
    }

    private ContainerTask parseContainerTask(JsonObject jsonContainerTask, long checklistId){
        return null;
    }


    @Override
    public boolean deleteTask(long taskId) {
        return false;
    }

    @Override
    public boolean editTask(Task task) {
        return false;
    }

    @Override
    public boolean pushTask(Task task) {
        return false;
    }

    @Override
    public void loadChecklists(ChecklistListCallback checklistListCallback) {

    }
}
