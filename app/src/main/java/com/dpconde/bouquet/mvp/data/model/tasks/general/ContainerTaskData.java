package com.dpconde.bouquet.mvp.data.model.tasks.general;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by dpconde on 13/02/2019.
 */

@Entity
public class ContainerTaskData {

    @Id
    private long id;

    @Generated(hash = 1231679868)
    public ContainerTaskData(long id) {
        this.id = id;
    }

    @Generated(hash = 1318461717)
    public ContainerTaskData() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
