package com.dpconde.bouquet.mvp.data.model;


/**
 * Created by dpconde on 28/9/18.
 */

public class LoginParams{

    private String username;
    private String password;
    private String service;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}