package com.dpconde.taskexecutor.mvp.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Date;
import org.greenrobot.greendao.DaoException;

/**
 * Created by dpconde on 13/02/2019.
 */

@Entity
public class Task implements Parcelable {

    @Id
    private long id;
    private String type;
    private String description;
    private Date executionDate;
    private String executionstatus;
    private String syncStatus;


    @ToOne(joinProperty = "executorId")
    private User executor;
    private Long executorId;
    

    protected Task(Parcel in) {
    }

    @Generated(hash = 169307857)
    public Task(long id, String type, String description, Date executionDate,
            String executionstatus, String syncStatus, Long executorId) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.executionDate = executionDate;
        this.executionstatus = executionstatus;
        this.syncStatus = syncStatus;
        this.executorId = executorId;
    }

    @Generated(hash = 733837707)
    public Task() {
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

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getExecutionDate() {
        return this.executionDate;
    }

    public void setExecutionDate(Date executionDate) {
        this.executionDate = executionDate;
    }

    public String getExecutionstatus() {
        return this.executionstatus;
    }

    public void setExecutionstatus(String executionstatus) {
        this.executionstatus = executionstatus;
    }

    public String getSyncStatus() {
        return this.syncStatus;
    }

    public void setSyncStatus(String syncStatus) {
        this.syncStatus = syncStatus;
    }

    public Long getExecutorId() {
        return this.executorId;
    }

    public void setExecutorId(Long executorId) {
        this.executorId = executorId;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1793016427)
    public User getExecutor() {
        Long __key = this.executorId;
        if (executor__resolvedKey == null || !executor__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UserDao targetDao = daoSession.getUserDao();
            User executorNew = targetDao.load(__key);
            synchronized (this) {
                executor = executorNew;
                executor__resolvedKey = __key;
            }
        }
        return executor;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 545454505)
    public void setExecutor(User executor) {
        synchronized (this) {
            this.executor = executor;
            executorId = executor == null ? null : executor.getId();
            executor__resolvedKey = executorId;
        }
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1442741304)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getTaskDao() : null;
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1469429066)
    private transient TaskDao myDao;
    @Generated(hash = 719827570)
    private transient Long executor__resolvedKey;
}