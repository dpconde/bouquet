package com.dpconde.taskexecutor.mvp.data.api.retrofit.responseobjects;

import com.dpconde.taskexecutor.mvp.data.api.TestDataManager;
import com.dpconde.taskexecutor.mvp.data.model.Checklist;
import com.dpconde.taskexecutor.mvp.data.model.Task;
import com.dpconde.taskexecutor.mvp.view.checklistlist.ChecklistListCallback;

/**
 * Created by dpconde on 28/9/18.
 */

public class LoginResponseDTO {


    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
