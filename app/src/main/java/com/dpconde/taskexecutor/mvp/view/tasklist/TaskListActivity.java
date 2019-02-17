package com.dpconde.taskexecutor.mvp.view.tasklist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dpconde.taskexecutor.R;
import com.dpconde.taskexecutor.UserListApplication;
import com.dpconde.taskexecutor.di.component.AppComponent;
import com.dpconde.taskexecutor.di.component.DaggerTaskListComponent;
import com.dpconde.taskexecutor.di.module.ContextModule;
import com.dpconde.taskexecutor.di.module.view.TaskListModule;
import com.dpconde.taskexecutor.mvp.data.model.User;
import com.dpconde.taskexecutor.mvp.view.tasklist.UserDetailPresenter;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

public class TaskListActivity extends AppCompatActivity  implements UserDetailPresenter.View, View.OnClickListener {

    @Inject
    UserDetailPresenter presenter;

    //Views
    private TextView location, inputFirstName, inputLastName;
    private ImageView imageView;
    private Button deleteUserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupComponent(UserListApplication.getApp().component());
        setContentView(R.layout.activity_user_detail);

        //Get user from DB
        //Long currentUserId = getIntent().getLongExtra("userIntent", 0L);
        //presenter.getUserFromUserId(currentUserId);

        initViews();
        loadViews(presenter.getCurrentUser());
    }


    /**
     * Inject dependencies method
     * @param appComponent
     */
    protected void setupComponent(AppComponent appComponent) {

        DaggerTaskListComponent.builder()
                .appComponent(appComponent)
                .taskListModule(new TaskListModule(this))
                .contextModule(new ContextModule(this))
                .build()
                .inject(this);
    }

    /**
     * Instantiate view components
     */
    private void initViews() {

        inputFirstName = findViewById(R.id.inputUserFirstName);
        inputLastName = findViewById(R.id.inputUserLastName);
        location = findViewById(R.id.userDetailLocationValue);
        imageView = findViewById(R.id.userDetailImage);
        deleteUserButton = findViewById(R.id.deleteUser);
        deleteUserButton.setOnClickListener(this);
    }

    /**
     * Load views content for the given user
     * @param user
     */
    private void loadViews(User user){
       // inputFirstName.setText(user.getName().getFirst());
       // inputLastName.setText(user.getName().getLast());
       // location.setText(user.getLocation().toString());
       // Picasso.get().load(user.getPicture().getLarge()).into(imageView);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.deleteUser:
               // presenter.deleteUser();
                break;
            default:
                break;
        }
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void finishView() {
        finish();
    }
}
