package com.dpconde.taskexecutor.mvp.data.api.jsonreader;

import android.content.Context;

import com.dpconde.taskexecutor.R;
import com.dpconde.taskexecutor.mvp.Constants;
import com.dpconde.taskexecutor.mvp.data.model.Checklist;
import com.dpconde.taskexecutor.mvp.data.model.TaskType;
import com.dpconde.taskexecutor.mvp.data.model.tasks.general.ChecklistTask;
import com.dpconde.taskexecutor.mvp.data.model.tasks.general.ContainerTask;
import com.dpconde.taskexecutor.mvp.data.model.tasks.general.GeneralTask;
import com.dpconde.taskexecutor.mvp.data.model.tasks.general.PictureTask;
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
            int type = task.get("type").getAsJsonObject().get("id").getAsInt();

            switch(type) {
                case Constants.TASK_TYPE_GENERAL_ID: //GeneralViewHolder Task
                    tasks.add(parseGeneralTask(task, id));
                    break;
                case Constants.TASK_TYPE_CONTAINER_ID: //ContainerViewHolder Task
                    tasks.add(parseContainerTask(task, id));
                    break;
                case Constants.TASK_TYPE_CHECKLIST_ID: //Checklist Task
                    tasks.add(parseChecklistTask(task, id));
                    break;
                case Constants.TASK_TYPE_PICTURE_ID: //Picture Task
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
        ChecklistTask checklistTask = new ChecklistTask();
        this.parseCommonFields(checklistTask, jsonChecklistTask, checklistId);
        return checklistTask;
    }

    private GeneralTask parseGeneralTask(JsonObject jsonGeneralTask, long checklistId){
        GeneralTask generalTask = new GeneralTask();
        this.parseCommonFields(generalTask, jsonGeneralTask, checklistId);
        return generalTask;
    }

    private PictureTask parsePictureTask(JsonObject jsonPictureTask, long checklistId){
        PictureTask pictureTask = new PictureTask();
        this.parseCommonFields(pictureTask, jsonPictureTask, checklistId);
        return pictureTask;
    }

    private ContainerTask parseContainerTask(JsonObject jsonContainerTask, long checklistId){
        ContainerTask containerTask = new ContainerTask();
        this.parseCommonFields(containerTask, jsonContainerTask, checklistId);
        return containerTask;
    }

    private Task parseCommonFields(Task task, JsonObject jsonTask, long checklistId){

        task.setId(jsonTask.get("id").getAsLong());

        //Task type
        JsonObject type = jsonTask.get("type").getAsJsonObject();
        TaskType taskType = new TaskType();
        taskType.setId(type.get("id").getAsLong());
        taskType.setDescription(type.get("description").getAsString());
        task.setTaskType(taskType);

        task.setDescription(jsonTask.get("description").getAsString());
        task.setDepth(jsonTask.get("depth").getAsInt());

        if(!jsonTask.get("parentTask").isJsonNull()){
            task.setParentTaskId(jsonTask.get("parentTask").getAsLong());
        }

        task.setOrder(jsonTask.get("order").getAsInt());
        task.setChecklistId(checklistId);

        return task;
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

    @Override
    public Checklist loadChecklist(ChecklistListCallback checklistListCallback, long checklistID) {

        Checklist checklist = null;

        try {
            InputStream inputStream = context.getResources().openRawResource(R.raw.checklist);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, bytes.length);
            String json = new String(bytes);

            //Json to Properties
            JsonObject jsonObject = gson.fromJson( json, JsonObject.class);

            checklist = parseJsonChecklist(jsonObject);

            if(checklistListCallback != null){
                checklistListCallback.onRetrievedChecklist(checklist);
            }

        } catch (IOException e) {

        }

        return checklist;
    }

    @Override
    public Checklist saveChecklist(Checklist checklist) {
        return null;
    }
}
