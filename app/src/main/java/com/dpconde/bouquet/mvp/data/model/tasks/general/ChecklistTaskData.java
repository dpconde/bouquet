package com.dpconde.bouquet.mvp.data.model.tasks.general;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by dpconde on 13/02/2019.
 */
@Entity
public class ChecklistTaskData{

    @Id
    private long id;

    @Generated(hash = 1552369214)
    public ChecklistTaskData(long id) {
        this.id = id;
    }

    @Generated(hash = 697499174)
    public ChecklistTaskData() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
