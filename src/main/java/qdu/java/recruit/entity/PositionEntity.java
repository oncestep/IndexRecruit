package qdu.java.recruit.entity;

import java.sql.Date;

public class PositionEntity {

    private int positionId;
    private String title;
    private String requirement;
    private int quantity;
    private String workCity;
    private int salaryUp;
    private int salaryDown;
    private Date releaseDate;
    private Date validDate;
    private int state;
    private int hits;
    private int categoryId;
    private int departmentId;
    private int hrId;

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getWorkCity() {
        return workCity;
    }

    public void setWorkCity(String workCity) {
        this.workCity = workCity;
    }

    public int getSalaryUp() {
        return salaryUp;
    }

    public void setSalaryUp(int salaryUp) {
        this.salaryUp = salaryUp;
    }

    public int getSalaryDown() {
        return salaryDown;
    }

    public void setSalaryDown(int salaryDown) {
        this.salaryDown = salaryDown;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getHits() { return hits; }

    public void setHits(int hits) { this.hits = hits; }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getHrId() {
        return hrId;
    }

    public void setHrId(int hrId) {
        this.hrId = hrId;
    }
}
