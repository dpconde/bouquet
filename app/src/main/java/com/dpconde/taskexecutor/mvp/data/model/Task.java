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
    private String description;
    private Date executionDate;
    private String executionStatus;
    private String syncStatus;
    private long checklistId;
    private long parentTaskId;
    private int order;
    private int depth;


    @ToOne(joinProperty = "executorId")
    private User executor;
    private Long executorId;

    @ToOne(joinProperty = "taskTypeId")
    private TaskType taskType;
    private Long taskTypeId;
    

    protected Task(Parcel in) {
    }


    @Generated(hash = 1519386493)
    public Task(long id, String description, Date executionDate,
            String executionStatus, String syncStatus, long checklistId,
            long parentTaskId, int order, int depth, Long executorId,
            Long taskTypeId) {
        this.id = id;
        this.description = description;
        this.executionDate = executionDate;
        this.executionStatus = executionStatus;
        this.syncStatus = syncStatus;
        this.checklistId = checklistId;
        this.parentTaskId = parentTaskId;
        this.order = order;
        this.depth = depth;
        this.executorId = executorId;
        this.taskTypeId = taskTypeId;
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


    public String getExecutionStatus() {
        return this.executionStatus;
    }


    public void setExecutionStatus(String executionStatus) {
        this.executionStatus = executionStatus;
    }


    public String getSyncStatus() {
        return this.syncStatus;
    }


    public void setSyncStatus(String syncStatus) {
        this.syncStatus = syncStatus;
    }


    public long getChecklistId() {
        return this.checklistId;
    }


    public void setChecklistId(long checklistId) {
        this.checklistId = checklistId;
    }


    public long getParentTaskId() {
        return this.parentTaskId;
    }


    public void setParentTaskId(long parentTaskId) {
        this.parentTaskId = parentTaskId;
    }


    public int getOrder() {
        return this.order;
    }


    public void setOrder(int order) {
        this.order = order;
    }


    public int getDepth() {
        return this.depth;
    }


    public void setDepth(int depth) {
        this.depth = depth;
    }


    public Long getExecutorId() {
        return this.executorId;
    }


    public void setExecutorId(Long executorId) {
        this.executorId = executorId;
    }


    public Long getTaskTypeId() {
        return this.taskTypeId;
    }


    public void setTaskTypeId(Long taskTypeId) {
        this.taskTypeId = taskTypeId;
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


    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1212221738)
    public TaskType getTaskType() {
        Long __key = this.taskTypeId;
        if (taskType__resolvedKey == null || !taskType__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TaskTypeDao targetDao = daoSession.getTaskTypeDao();
            TaskType taskTypeNew = targetDao.load(__key);
            synchronized (this) {
                taskType = taskTypeNew;
                taskType__resolvedKey = __key;
            }
        }
        return taskType;
    }


    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1758672895)
    public void setTaskType(TaskType taskType) {
        synchronized (this) {
            this.taskType = taskType;
            taskTypeId = taskType == null ? null : taskType.getId();
            taskType__resolvedKey = taskTypeId;
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
    @Generated(hash = 414322750)
    private transient Long taskType__resolvedKey;



}
