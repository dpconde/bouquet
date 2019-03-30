package com.dpconde.taskexecutor.mvp.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Date;

/**
 * Created by dpconde on 13/02/2019.
 */

@Entity
public class TaskType implements Parcelable {

    @Id
    private long id;
    private String description;


    protected TaskType(Parcel in) {
    }

    @Generated(hash = 2139984144)
    public TaskType(long id, String description) {
        this.id = id;
        this.description = description;
    }

    @Generated(hash = 1773832039)
    public TaskType() {
    }
    
    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static final Creator<TaskType> CREATOR = new Creator<TaskType>() {
        @Override
        public TaskType createFromParcel(Parcel in) {
            return new TaskType(in);
        }

        @Override
        public TaskType[] newArray(int size) {
            return new TaskType[size];
        }
    };



}
