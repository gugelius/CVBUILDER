package com.example.testproject.service.impl;

import com.example.testproject.dao.SkillDao;
import com.example.testproject.dao.impl.SkillDaoImpl;
import com.example.testproject.entity.Skill;
import com.example.testproject.service.SkillService;
import java.util.List;

public class SkillServiceImpl implements SkillService{
    private static SkillServiceImpl instance = new SkillServiceImpl();
    private final SkillDao skillDao = SkillDaoImpl.getInstance();
    public static SkillServiceImpl getInstance() {
        return instance;
    }
    private SkillServiceImpl() {
    }
    @Override
    public void saveSkill(Skill skill, int ResumeID){
        skillDao.save(skill, ResumeID);
    }
    @Override
    public List<Skill> loadSkills(int resumeId) {
        return skillDao.loadSkill(resumeId);
    }
    @Override
    public boolean updateSkill(Skill skill){
        return skillDao.updateSkill(skill);
    }
    @Override
    public void deleteSkill(int skillId) {
        skillDao.deleteSkill(skillId);
    }
    @Override
    public void deleteAllSkill(int resumeId){
        skillDao.deleteAllSkill(resumeId);
    }
}
