package qdu.java.recruit.entity;

public class HREntity {

    private int hrId;
    private String hrMobile;
    private String hrPassword;
    private String hrName;
    private String hrEmail;
    private String description;
    private int departmentId;

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

    public String getHrPassword() {
        return hrPassword;
    }

    public void setHrPassword(String hrPassword) {
        this.hrPassword = hrPassword;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
}
