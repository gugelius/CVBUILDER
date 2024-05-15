package com.example.testproject.entity;

public class Education extends AbstractEntity{
    private int educationId;
    private int resumeId;
    private boolean isNew;
    private String name;
    private String degree;
    private String city;
    private String startYear;
    private String endYear;
    public Education(int educationId, int resumeId, boolean isNew, String name, String degree, String city, String startYear, String endYear){
        this.educationId = educationId;
        this.resumeId = resumeId;
        this.isNew = isNew;
        this.name = name;
        this.degree = degree;
        this.city = city;
        this.startYear = startYear;
        this.endYear = endYear;
    }

    public int getEducationId() {
        return educationId;
    }
    public int getResumeId() {
        return resumeId;
    }
    public void setResumeId(int resumeId) {
        this.resumeId = resumeId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDegree() {
        return degree;
    }
    public String getCity() {
        return city;
    }
    public String getStartYear() {
        return startYear;
    }
    public String getEndYear() {
        return endYear;
    }
    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }
    public boolean getIsNew(){
        return isNew;
    }
}
