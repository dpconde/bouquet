package com.dpconde.taskexecutor.mvp.data.api.retrofit.responseobjects.login;

import java.util.List;

/**
 * Created by dpconde on 28/9/18.
 */

public class ResponseDTO {

    private List<String> errors;
    private List<String> info;
    private LoginResponseDTO result;

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getInfo() {
        return info;
    }

    public void setInfo(List<String> info) {
        this.info = info;
    }

    public LoginResponseDTO getResult() {
        return result;
    }

    public void setResult(LoginResponseDTO result) {
        this.result = result;
    }
}
