package com.example.testproject.service;

import com.example.testproject.entity.Skill;
import java.util.List;

public interface SkillService {
    void saveSkill(Skill skill, int ResumeID);
    List<Skill> loadSkills(int resumeId);
    boolean updateSkill(Skill skill);
    void deleteSkill(int skillId);
    void deleteAllSkill(int resumeId);
}