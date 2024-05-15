package com.example.testproject.dao;

import com.example.testproject.entity.Skill;
import java.util.List;

public interface SkillDao {
    boolean save(Skill skill, int ResumeID);
    List<Skill> loadSkill(int resumeId);
    boolean updateSkill(Skill skill);
    void deleteSkill(int skillId);
    void deleteAllSkill(int resumeId);
}
