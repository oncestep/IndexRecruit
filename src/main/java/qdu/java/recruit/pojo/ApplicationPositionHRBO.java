package qdu.java.recruit.pojo;

import qdu.java.recruit.entity.HREntity;
import qdu.java.recruit.entity.PositionEntity;

import java.sql.Timestamp;

public class ApplicationPositionHRBO extends PositionEntity{

    private int applicationId;
    private int state;
    private Timestamp recentTime;
    private int resumeId;

    private int hrId;
    private String hrMobile;
    private String hrName;
    private String hrEmail;

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Timestamp getRecentTime() {
        return recentTime;
    }

    public void setRecentTime(Timestamp recentTime) {
        this.recentTime = recentTime;
    }

    public int getResumeId() {
        return resumeId;
    }

    public void setResumeId(int resumeId) {
        this.resumeId = resumeId;
    }

    public int getHrId() {
        return hrId;
    }

    public void setHrId(int hrId) {
        this.hrId = hrId;
    }

    public String getHrMobile() {
        return hrMobile;
    }

    public void setHrMobile(String hrMobile) {
        this.hrMobile = hrMobile;
    }

    public String getHrName() {
        return hrName;
    }

    public void setHrName(String hrName) {
        this.hrName = hrName;
    }

    public String getHrEmail() {
        return hrEmail;
    }

    public void setHrEmail(String hrEmail) {
        this.hrEmail = hrEmail;
    }
}
