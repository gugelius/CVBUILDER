package com.example.testproject.service.impl;

import com.example.testproject.dao.ExperienceDao;
import com.example.testproject.dao.impl.ExperienceDaoImpl;
import com.example.testproject.entity.Experience;
import com.example.testproject.service.ExperienceService;

public class ExperienceServiceImpl implements ExperienceService{
    private static ExperienceServiceImpl instance = new ExperienceServiceImpl();
    private final ExperienceDao experienceDao = ExperienceDaoImpl.getInstance();

    public static ExperienceServiceImpl getInstance() {
        return instance;
    }
    private ExperienceServiceImpl() {
    }
    @Override
    public void saveExperience(Experience experience, int ResumeID){
        experienceDao.save(experience,ResumeID);
    }
}
