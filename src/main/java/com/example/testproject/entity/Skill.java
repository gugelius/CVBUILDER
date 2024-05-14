package com.example.testproject.entity;

public class Skill {
    private int skillId;
    private int resumeId;
    private int name;
    private int level;

    public Skill(int resumeId, int name, int level) {
        this.resumeId = resumeId;
        this.name = name;
        this.level = level;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public int getResumeId() {
        return resumeId;
    }

    public void setResumeId(int resumeId) {
        this.resumeId = resumeId;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
