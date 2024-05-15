package com.example.testproject.entity;

public class Skill extends AbstractEntity{
    private int skillId;
    private int resumeId;
    private boolean isNew;
    private String name;
    private int level;
    public Skill(int skillId, int resumeId, boolean isNew, String name, int level) {
        this.skillId = skillId;
        this.resumeId = resumeId;
        this.isNew = isNew;
        this.name = name;
        this.level = level;
    }
    public int getSkillId() {
        return skillId;
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
    public int getLevel() {
        return level;
    }
    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }
    public boolean getIsNew() {
        return isNew;
    }
}
