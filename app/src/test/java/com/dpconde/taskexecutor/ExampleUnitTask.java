package com.dpconde.taskexecutor;

import com.dpconde.taskexecutor.mvp.data.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTask {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);


        User user;
        List<User> userList = new ArrayList<>();
        for(int i = 0; i<10; i++){
            user = new User();
            user.setEmail("user"+i+"@gmail.com");
            user.setUserCode("AT015421"+i);
            user.setPassword("password"+i);
            user.setId((long)i);
            userList.add(user);
        }

        Gson gson = new Gson();
        Type listOfTestObject = new TypeToken<List<User>>(){}.getType();
        String s = gson.toJson(userList, listOfTestObject);

       int a = 0;


    }
}