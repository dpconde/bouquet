package com.dpconde.bouquet.mvp.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.OrderBy;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.DaoException;


/**
 * Created by dpconde on 28/9/18.
 */

@Entity
public class Checklist implements Parcelable {

    @Id
    private Long id;
    private String description;
    private String projectName;
    private String samples;
    private String workingOrder;
    private String customer;

    @ToMany(referencedJoinProperty = "checklistId")
    @OrderBy("order ASC")
    private List<Task> tasks;




    protected Checklist(Parcel in) {
    }



    @Generated(hash = 920025163)
    public Checklist(Long id, String description, String projectName, String samples,
            String workingOrder, String customer) {
        this.id = id;
        this.description = description;
        this.projectName = projectName;
        this.samples = samples;
        this.workingOrder = workingOrder;
        this.customer = customer;
    }



    @Generated(hash = 1106849191)
    public Checklist() {
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }



    public Long getId() {
        return this.id;
    }



    public void setId(Long id) {
        this.id = id;
    }



    public String getDescription() {
        return this.description;
    }



    public void setDescription(String description) {
        this.description = description;
    }



    public String getProjectName() {
        return this.projectName;
    }



    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }



    public String getSamples() {
        return this.samples;
    }



    public void setSamples(String samples) {
        this.samples = samples;
    }



    public String getWorkingOrder() {
        return this.workingOrder;
    }



    public void setWorkingOrder(String workingOrder) {
        this.workingOrder = workingOrder;
    }



    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 749112413)
    public List<Task> getTasks() {
        if (tasks == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TaskDao targetDao = daoSession.getTaskDao();
            List<Task> tasksNew = targetDao._queryChecklist_Tasks(id);
            synchronized (this) {
                if (tasks == null) {
                    tasks = tasksNew;
                }
            }
        }
        return tasks;
    }



    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 668181820)
    public synchronized void resetTasks() {
        tasks = null;
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



    public static final Creator<Checklist> CREATOR = new Creator<Checklist>() {
        @Override
        public Checklist createFromParcel(Parcel in) {
            return new Checklist(in);
        }

        @Override
        public Checklist[] newArray(int size) {
            return new Checklist[size];
        }
    };
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1073349481)
    private transient ChecklistDao myDao;

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }



    public String getCustomer() {
        return this.customer;
    }



    public void setCustomer(String customer) {
        this.customer = customer;
    }



    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2049759880)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getChecklistDao() : null;
    }
}