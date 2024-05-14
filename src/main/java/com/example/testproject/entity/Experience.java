package com.example.testproject.entity;

import java.util.Date;

public class Experience extends AbstractEntity{
    private int experienceId;
    private int resumeId;
    private String position;
    private String company;
    private String city;
    private String startDate;
    private String endDate;
    public Experience(int experienceId,int resumeId, String position, String company, String city, String startDate, String endDate){
        this.experienceId = experienceId;
        this.resumeId = resumeId;
        this.position = position;
        this.company = company;
        this.city = city;
        this. startDate = startDate;
        this.endDate = endDate;
    }

    public int getExperienceId() {
        return experienceId;
    }

    public void setExperienceId(int experienceId) {
        this.experienceId = experienceId;
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

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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
}
