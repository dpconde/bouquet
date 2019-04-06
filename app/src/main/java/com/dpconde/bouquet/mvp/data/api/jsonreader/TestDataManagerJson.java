package com.dpconde.bouquet.mvp.data.api.jsonreader;

import android.content.Context;

import com.dpconde.bouquet.R;
import com.dpconde.bouquet.mvp.Constants;
import com.dpconde.bouquet.mvp.data.model.Checklist;
import com.dpconde.bouquet.mvp.data.model.TaskType;
import com.dpconde.bouquet.mvp.data.model.tasks.general.ChecklistTaskData;
import com.dpconde.bouquet.mvp.data.model.tasks.general.ContainerTaskData;
import com.dpconde.bouquet.mvp.data.model.tasks.general.GeneralTaskData;
import com.dpconde.bouquet.mvp.data.model.tasks.misuse.MisuseTaskData;
import com.dpconde.bouquet.mvp.data.model.tasks.misuse.MisuseDriver;
import com.dpconde.bouquet.mvp.view.checklistlist.ChecklistListCallback;
import com.dpconde.bouquet.mvp.data.api.TestDataManager;
import com.dpconde.bouquet.mvp.data.model.Task;
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
                case Constants.TASK_TYPE_MISUSE_ID: //Misuse Task
                    tasks.add(parseMisuseTask(task, id));
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

    private Task parseChecklistTask(JsonObject jsonChecklistTask, long checklistId){
        ChecklistTaskData checklistTaskData = new ChecklistTaskData();

        Task task = this.parseCommonFields(jsonChecklistTask, checklistId);
        task.setChecklistTaskData(checklistTaskData);

        return task;
    }

    private Task parseGeneralTask(JsonObject jsonGeneralTask, long checklistId){
        GeneralTaskData generalTaskData = new GeneralTaskData();

        Task task = this.parseCommonFields(jsonGeneralTask, checklistId);
        task.setGeneralTaskData(generalTaskData);

        return task;
    }


    private Task parseContainerTask(JsonObject jsonContainerTask, long checklistId){
        ContainerTaskData containerData = new ContainerTaskData();

        Task task = this.parseCommonFields(jsonContainerTask, checklistId);
        task.setContainerTaskData(containerData);

        return task;
    }

    private Task parseMisuseTask(JsonObject jsonMisuseTask, long checklistId){
        MisuseTaskData misuseTaskData = new MisuseTaskData();

        //Specific fields
        JsonObject data = jsonMisuseTask.get("data").getAsJsonObject();
        misuseTaskData.setTestName(data.get("testName").getAsString());
        misuseTaskData.setOriginalTest(data.get("originalTest").getAsString());
        misuseTaskData.setIdiadaTest(data.get("IDIADATest").getAsString());
        misuseTaskData.setSpeed(data.get("speed").getAsString());
        misuseTaskData.setBrake(data.get("brake").getAsString());
        misuseTaskData.setWeight(data.get("weight").getAsString());
        misuseTaskData.setTyre(data.get("tyre").getAsString());
        misuseTaskData.setTyrePressure(data.get("tyrePressure").getAsString());
        misuseTaskData.setEngRemarks(data.get("remarks").getAsString());

        //Driver list
        List<MisuseDriver> driverList = new ArrayList<>();
        JsonArray jsonDriverList = data.get("driverList").getAsJsonArray();
        for(JsonElement driver: jsonDriverList){
            driverList.add(gson.fromJson(driver, MisuseDriver.class));
        }
        misuseTaskData.setDriverList(driverList);

        Task task = this.parseCommonFields(jsonMisuseTask, checklistId);
        task.setMisuseTaskData(misuseTaskData);

        return task;
    }


    private Task parseCommonFields(JsonObject jsonTask, long checklistId){

        Task task = new Task();

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
