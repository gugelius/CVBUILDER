package com.example.testproject.service.impl;

import com.example.testproject.dao.EducationDao;
import com.example.testproject.dao.impl.EducationDaoImpl;
import com.example.testproject.entity.Education;
import com.example.testproject.service.EducationService;
import java.util.List;

public class EducationServiceImpl implements EducationService{
    private static EducationServiceImpl instance = new EducationServiceImpl();
    private final EducationDao educationDao = EducationDaoImpl.getInstance();
    public static EducationServiceImpl getInstance() {
        return instance;
    }
    private EducationServiceImpl() {
    }
    @Override
    public void saveEducation(Education education, int ResumeID){
        educationDao.save(education, ResumeID);
    }
    @Override
    public List<Education> loadEducations(int resumeId) {
        return educationDao.loadEducations(resumeId);
    }
    @Override
    public boolean updateEducation(Education education){
        return educationDao.updateEducation(education);
    }
    @Override
    public void deleteEducation(int educationId) {
        educationDao.deleteEducation(educationId);
    }
    @Override
    public void deleteAllEducation(int resumeId){
        educationDao.deleteAllEducation(resumeId);
    }
}
