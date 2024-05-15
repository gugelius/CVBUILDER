package com.example.testproject.service.impl;

import com.example.testproject.dao.ExperienceDao;
import com.example.testproject.dao.impl.ExperienceDaoImpl;
import com.example.testproject.entity.Experience;
import com.example.testproject.service.ExperienceService;

import java.util.List;

public class ExperienceServiceImpl implements ExperienceService{
    private static ExperienceServiceImpl instance = new ExperienceServiceImpl();
    private final ExperienceDao experienceDao = ExperienceDaoImpl.getInstance();
    public static ExperienceServiceImpl getInstance() {
        return instance;
    }
    private ExperienceServiceImpl() {
    }
    @Override
    public void saveExperience(Experience experience, int ResumeId){
        experienceDao.save(experience,ResumeId);
    }
    @Override
    public List<Experience> loadExperiences(int resumeId) {
        return experienceDao.loadExperience(resumeId);
    }
    @Override
    public boolean updateExperience(Experience experience){
            return experienceDao.updateExperience(experience);
    }
    @Override
    public void deleteExperience(int experienceId) {
        experienceDao.deleteExperience(experienceId);
    }
    @Override
    public void deleteAllExperience(int resumeId){
        experienceDao.deleteAllExperience(resumeId);
    }
}
