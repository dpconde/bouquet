package com.dpconde.bouquet.mvp.data.model.tasks.misuse;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;


/**
 * Created by dpconde on 30/03/2019.
 */

@Entity
public class MisuseDriver {

    @Id
    private long id;
    private String name;
    private long misuseTestId;

    @Generated(hash = 1733958015)
    public MisuseDriver(long id, String name, long misuseTestId) {
        this.id = id;
        this.name = name;
        this.misuseTestId = misuseTestId;
    }

    @Generated(hash = 751723249)
    public MisuseDriver() {
    }

    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public long getMisuseTestId() {
        return this.misuseTestId;
    }
    public void setMisuseTestId(long misuseTestId) {
        this.misuseTestId = misuseTestId;
    }

}
