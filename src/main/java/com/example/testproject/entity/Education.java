package com.example.testproject.entity;

public class Education extends AbstractEntity{
    private int educationId;
    private int resumeId;
    private String name;
    private String degree;
    private String city;
    private String startDate;
    private String endDate;
    public Education(int educationId, int resumeId, String name, String degree, String city, String startDate, String endDate){
        this.educationId = educationId;
        this.resumeId = resumeId;
        this.name = name;
        this.degree = degree;
        this.city = city;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getEducationId() {
        return educationId;
    }

    public void setEducationId(int educationId) {
        this.educationId = educationId;
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

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    // Геттеры и сеттеры для каждого поля...
    // ...
}
