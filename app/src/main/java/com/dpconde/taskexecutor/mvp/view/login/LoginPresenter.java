package com.dpconde.taskexecutor.mvp.view.login;

import android.content.Context;
import android.content.Intent;

import com.dpconde.taskexecutor.mvp.data.api.Callback;
import com.dpconde.taskexecutor.mvp.data.api.UserManager;
import com.dpconde.taskexecutor.mvp.data.model.User;
import com.dpconde.taskexecutor.mvp.view.tasklist.TaskListActivity;

import java.util.List;

/**
 * Created by dpconde on 28/9/18.
 */

public class LoginPresenter implements Callback{

    //Dependencies
    private View view;
    private UserManager userManager;
    private Context context;



    public LoginPresenter(View view, UserManager userManager, Context context) {
        this.view = view;
        this.userManager = userManager;
        this.context = context;
    }


    /**
     * Start user detail Activity for the selected user
     * @param user
     */
    public void onItemClicked(User user) {
        Intent intent = new Intent(context, TaskListActivity.class);
        intent.putExtra("userIntent", user.getId());
        context.startActivity(intent);
    }

    @Override
    public void onRetrievedUsers(List<User> userList) {
        view.hideProgress();
    }


    /**
     * Read userCode and Password fields and try to do login
     * @param userCode
     * @param password
     */
    public void doLogin(String userCode, String password){

    }




    public interface View {

        /**
         * Show loading image and hide user list
         */
        void showProgress();

        /**
         * Hide loading image and show user list
         */
        void hideProgress();

    }
}
