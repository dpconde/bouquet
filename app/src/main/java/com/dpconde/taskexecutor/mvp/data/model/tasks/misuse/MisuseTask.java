package com.dpconde.taskexecutor.mvp.data.model.tasks.misuse;

import android.os.Parcelable;

import com.dpconde.taskexecutor.mvp.data.model.Task;


import org.greenrobot.greendao.annotation.Entity;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by dpconde on 30/03/2019.
 */

@Entity
public class MisuseTask extends Task implements Parcelable {

    //Test definition
    private String testName;
    private String originalTest;
    private String idiadaTest;
    private String speed;
    private String brake;
    private String weight;
    private String tyre;
    private String tyrePressure;
    private String engRemarks;

    //Test execution
    private Date executionDate;
    private Boolean SRSActivation;
    private String SRSModulesActivated;
    private Boolean damages;
    private String replacedParts;
    private String repairedParts;
    private Boolean testFinished;




    @Generated(hash = 1226995402)
    public MisuseTask(String testName, String originalTest, String idiadaTest,
            String speed, String brake, String weight, String tyre,
            String tyrePressure, String engRemarks, Date executionDate,
            Boolean SRSActivation, String SRSModulesActivated, Boolean damages,
            String replacedParts, String repairedParts, Boolean testFinished) {
        this.testName = testName;
        this.originalTest = originalTest;
        this.idiadaTest = idiadaTest;
        this.speed = speed;
        this.brake = brake;
        this.weight = weight;
        this.tyre = tyre;
        this.tyrePressure = tyrePressure;
        this.engRemarks = engRemarks;
        this.executionDate = executionDate;
        this.SRSActivation = SRSActivation;
        this.SRSModulesActivated = SRSModulesActivated;
        this.damages = damages;
        this.replacedParts = replacedParts;
        this.repairedParts = repairedParts;
        this.testFinished = testFinished;
    }
    @Generated(hash = 512079185)
    public MisuseTask() {
    }
    public String getTestName() {
        return this.testName;
    }
    public void setTestName(String testName) {
        this.testName = testName;
    }
    public String getOriginalTest() {
        return this.originalTest;
    }
    public void setOriginalTest(String originalTest) {
        this.originalTest = originalTest;
    }
    public String getIdiadaTest() {
        return this.idiadaTest;
    }
    public void setIdiadaTest(String idiadaTest) {
        this.idiadaTest = idiadaTest;
    }
    public String getSpeed() {
        return this.speed;
    }
    public void setSpeed(String speed) {
        this.speed = speed;
    }
    public String getBrake() {
        return this.brake;
    }
    public void setBrake(String brake) {
        this.brake = brake;
    }
    public String getWeight() {
        return this.weight;
    }
    public void setWeight(String weight) {
        this.weight = weight;
    }
    public String getTyre() {
        return this.tyre;
    }
    public void setTyre(String tyre) {
        this.tyre = tyre;
    }
    public String getTyrePressure() {
        return this.tyrePressure;
    }
    public void setTyrePressure(String tyrePressure) {
        this.tyrePressure = tyrePressure;
    }
    public String getEngRemarks() {
        return this.engRemarks;
    }
    public void setEngRemarks(String engRemarks) {
        this.engRemarks = engRemarks;
    }
    public Date getExecutionDate() {
        return this.executionDate;
    }
    public void setExecutionDate(Date executionDate) {
        this.executionDate = executionDate;
    }
    public Boolean getSRSActivation() {
        return this.SRSActivation;
    }
    public void setSRSActivation(Boolean SRSActivation) {
        this.SRSActivation = SRSActivation;
    }
    public String getSRSModulesActivated() {
        return this.SRSModulesActivated;
    }
    public void setSRSModulesActivated(String SRSModulesActivated) {
        this.SRSModulesActivated = SRSModulesActivated;
    }
    public Boolean getDamages() {
        return this.damages;
    }
    public void setDamages(Boolean damages) {
        this.damages = damages;
    }
    public String getReplacedParts() {
        return this.replacedParts;
    }
    public void setReplacedParts(String replacedParts) {
        this.replacedParts = replacedParts;
    }
    public String getRepairedParts() {
        return this.repairedParts;
    }
    public void setRepairedParts(String repairedParts) {
        this.repairedParts = repairedParts;
    }
    public Boolean getTestFinished() {
        return this.testFinished;
    }
    public void setTestFinished(Boolean testFinished) {
        this.testFinished = testFinished;
    }




}
