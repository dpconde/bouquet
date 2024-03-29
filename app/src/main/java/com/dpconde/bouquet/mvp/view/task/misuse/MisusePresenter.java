package com.dpconde.bouquet.mvp.view.task.misuse;

import android.content.Context;
import android.content.Intent;

import com.dpconde.bouquet.mvp.data.api.TestDataManager;
import com.dpconde.bouquet.mvp.data.model.User;
import com.dpconde.bouquet.mvp.view.checklistlist.ChecklistListActivity;

/**
 * Created by dpconde on 28/9/18.
 */

public class MisusePresenter implements MisuseCallback {

    //Dependencies
    private View view;
    private Context context;

    //Business
    private TestDataManager dataManagerDB;


    public MisusePresenter(View view, Context context, TestDataManager dataManagerDB) {
        this.view = view;
        this.context = context;
        this.dataManagerDB = dataManagerDB;
    }


    /**
     * Start Checklist list activity
     * @param user
     */
    public void startChecklistListActivity(User user) {
        Intent intent = new Intent(context, ChecklistListActivity.class);
        context.startActivity(intent);
    }



    public interface View {

        /**
         * Show loading image and hide user list
         */
        void showLoading();

        /**
         * Hide loading image and show user list
         */
        void hideLoading();

        /**
         * Show message
         * @param messageResource
         */
        void showMessage(int messageResource);

    }
}
