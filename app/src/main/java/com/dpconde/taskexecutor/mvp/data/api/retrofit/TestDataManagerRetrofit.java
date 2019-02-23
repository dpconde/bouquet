package com.dpconde.taskexecutor.mvp.data.api.retrofit;

import com.dpconde.taskexecutor.mvp.view.checklistlist.ChecklistListCallback;
import com.dpconde.taskexecutor.mvp.data.api.TestDataManager;
import com.dpconde.taskexecutor.mvp.data.model.Task;

/**
 * Created by dpconde on 28/9/18.
 */

public class TestDataManagerRetrofit implements TestDataManager {

    private LoginApi loginApi;

    private final int NUM_RESULTS = 100;
    private final String SEED = "xmoba";

    public TestDataManagerRetrofit(LoginApi loginApi) {
        this.loginApi = loginApi;
    }

    @Override
    public void loadTasks(ChecklistListCallback checklistListCallback) {

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


    /*
    @Override
    public boolean deleteUser(String userId) {
        //TODO
        return false;
    }

    @Override
    public void getAllUsers(final ChecklistListCallback callback) {

        loginApi.getUsers(NUM_RESULTS, SEED).enqueue(new retrofit2.ChecklistListCallback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                List<User> userList = response.body().getResults();
                if(callback!=null){
                    callback.onRetrievedUsers(userList);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.e("RETROFIT", t.toString());
            }
        });
    }



    @Override
    public List<User> getPaginatedUsers(int pageNumber, int resultsPerPage) {
        //TODO
        return null;
    }

    @Override
    public User getUserById(Long userId) {
        //TODO
        return null;
    }

    @Override
    public User createUser(User user) {
        //TODO
        return null;
    }

    @Override
    public List<User> createUsers(List<User> userList) {
        //TODO
        return null;
    }

    @Override
    public int countTotalResults() {
        //TODO
        return 0;
    }

    @Override
    public void deleteAllUsers() {
        //TODO
    }

    @Override
    public void loadData(ChecklistListCallback callback, int pageNumber, int resultsPerPage) {
        //TODO
    }*/

}
