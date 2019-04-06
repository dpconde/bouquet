package com.dpconde.bouquet.mvp.data.model.tasks.misuse;

import android.os.Parcel;
import android.os.Parcelable;


import org.greenrobot.greendao.annotation.Entity;

import java.util.List;

import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.DaoException;
import com.dpconde.bouquet.mvp.data.model.DaoSession;

/**
 * Created by dpconde on 30/03/2019.
 */

@Entity
public class MisuseTaskData implements Parcelable {

    @Id
    long id;

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

    @ToMany(referencedJoinProperty = "misuseTestId")
    private List<MisuseDriver> driverList;

    //Test execution
    private Boolean SRSActivation;
    private String SRSModulesActivated;
    private Boolean damages;
    private String replacedParts;
    private String repairedParts;
    private Boolean testFinished;


    protected MisuseTaskData(Parcel in) {
        id = in.readLong();
        testName = in.readString();
        originalTest = in.readString();
        idiadaTest = in.readString();
        speed = in.readString();
        brake = in.readString();
        weight = in.readString();
        tyre = in.readString();
        tyrePressure = in.readString();
        engRemarks = in.readString();
        byte tmpSRSActivation = in.readByte();
        SRSActivation = tmpSRSActivation == 0 ? null : tmpSRSActivation == 1;
        SRSModulesActivated = in.readString();
        byte tmpDamages = in.readByte();
        damages = tmpDamages == 0 ? null : tmpDamages == 1;
        replacedParts = in.readString();
        repairedParts = in.readString();
        byte tmpTestFinished = in.readByte();
        testFinished = tmpTestFinished == 0 ? null : tmpTestFinished == 1;
    }

    @Generated(hash = 1662701553)
    public MisuseTaskData(long id, String testName, String originalTest, String idiadaTest,
            String speed, String brake, String weight, String tyre, String tyrePressure,
            String engRemarks, Boolean SRSActivation, String SRSModulesActivated,
            Boolean damages, String replacedParts, String repairedParts,
            Boolean testFinished) {
        this.id = id;
        this.testName = testName;
        this.originalTest = originalTest;
        this.idiadaTest = idiadaTest;
        this.speed = speed;
        this.brake = brake;
        this.weight = weight;
        this.tyre = tyre;
        this.tyrePressure = tyrePressure;
        this.engRemarks = engRemarks;
        this.SRSActivation = SRSActivation;
        this.SRSModulesActivated = SRSModulesActivated;
        this.damages = damages;
        this.replacedParts = replacedParts;
        this.repairedParts = repairedParts;
        this.testFinished = testFinished;
    }

    @Generated(hash = 80182642)
    public MisuseTaskData() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(testName);
        dest.writeString(originalTest);
        dest.writeString(idiadaTest);
        dest.writeString(speed);
        dest.writeString(brake);
        dest.writeString(weight);
        dest.writeString(tyre);
        dest.writeString(tyrePressure);
        dest.writeString(engRemarks);
        dest.writeByte((byte) (SRSActivation == null ? 0 : SRSActivation ? 1 : 2));
        dest.writeString(SRSModulesActivated);
        dest.writeByte((byte) (damages == null ? 0 : damages ? 1 : 2));
        dest.writeString(replacedParts);
        dest.writeString(repairedParts);
        dest.writeByte((byte) (testFinished == null ? 0 : testFinished ? 1 : 2));
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

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 59217594)
    public List<MisuseDriver> getDriverList() {
        if (driverList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            MisuseDriverDao targetDao = daoSession.getMisuseDriverDao();
            List<MisuseDriver> driverListNew = targetDao._queryMisuseTaskData_DriverList(id);
            synchronized (this) {
                if (driverList == null) {
                    driverList = driverListNew;
                }
            }
        }
        return driverList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1015145268)
    public synchronized void resetDriverList() {
        driverList = null;
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
    @Generated(hash = 1463678727)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getMisuseTaskDataDao() : null;
    }

    public static final Creator<MisuseTaskData> CREATOR = new Creator<MisuseTaskData>() {
        @Override
        public MisuseTaskData createFromParcel(Parcel in) {
            return new MisuseTaskData(in);
        }

        @Override
        public MisuseTaskData[] newArray(int size) {
            return new MisuseTaskData[size];
        }
    };

    public void setDriverList(List<MisuseDriver> driverList) {
        this.driverList = driverList;
    }

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 2108199979)
    private transient MisuseTaskDataDao myDao;
}
