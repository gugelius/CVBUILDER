package com.example.testproject.entity;

public class Resume extends AbstractEntity{
    private int userId;
    private int resumeId;
    private String title;
    private String date;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String adress;
    private String about;

    public Resume(int resumeId,int userId, String title, String date,String name, String surname, String email, String phone, String adress, String about) {
        this.userId = userId;
        this.resumeId = resumeId;
        this.title = title;
        this.date = date;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.adress = adress;
        this.about = about;
    }
    public void setUserId(int userId){
        this.userId=userId;
    }
    public int getUserId(){
    return userId;
    }
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
    public String getAbout(){
        return about;
    }
    public String getName() {
    return name;
    }
    public String getSurname() {
    return surname;
    }
    public String getPhone() {
    return phone;
    }
    public String getAdress() {
    return adress;
    }
    public String getEmail() {
    return email;
    }
}
