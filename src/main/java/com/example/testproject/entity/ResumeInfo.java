package com.example.testproject.entity;

public class ResumeInfo extends AbstractEntity{
    private int resumeId;
    private String title;
    private String date;
    public ResumeInfo(int resumeId, String title, String date) {
        this.resumeId = resumeId;
        this.title = title;
        this.date = date;
    }
    // Геттеры и сеттеры для каждого поля...
    public int getResumeId() {
        return resumeId;
    }
    public void setResumeId(int resumeId) {
        this.resumeId = resumeId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDate() {
        return date;
    }

}
