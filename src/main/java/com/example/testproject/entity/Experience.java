package com.example.testproject.entity;

public class Experience extends AbstractEntity{
    private int experienceId;
    private int resumeId;
    private boolean isNew;
    private String position;
    private String company;
    private String city;
    private String startDate;
    private String endDate;
    public Experience(int experienceId,int resumeId,boolean isNew, String position, String company, String city, String startDate, String endDate){
        this.experienceId = experienceId;
        this.resumeId = resumeId;
        this.isNew = isNew;
        this.position = position;
        this.company = company;
        this.city = city;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean getIsNew(){
        return isNew;
    }
    public void setIsNew(boolean isNew){
        this.isNew = isNew;
    }
    public int getExperienceId() {
        return experienceId;
    }
    public int getResumeId() {
        return resumeId;
    }
    public void setResumeId(int resumeId) {
        this.resumeId = resumeId;
    }
    public String getPosition() {
        return position;
    }
    public String getCompany() {
        return company;
    }
    public String getCity() {
        return city;
    }
    public String getStartDate() {
        return startDate;
    }
    public String getEndDate() {
        return endDate;
    }
}
