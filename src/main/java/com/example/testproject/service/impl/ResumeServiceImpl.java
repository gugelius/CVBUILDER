package com.example.testproject.service.impl;

import com.example.testproject.dao.ResumeDao;
import com.example.testproject.dao.impl.ResumeDaoImpl;
import com.example.testproject.entity.Resume;
import com.example.testproject.service.ResumeService;

public class ResumeServiceImpl implements ResumeService {
    private static ResumeServiceImpl instance = new ResumeServiceImpl();
    private final ResumeDao resumeDao = ResumeDaoImpl.getInstance();

    public static ResumeServiceImpl getInstance() {
        return instance;
    }
    private ResumeServiceImpl() {
    }
    @Override
    public int saveResume(Resume resume) {
        return resumeDao.save(resume);
    }
    @Override
    public Resume loadResume(int resumeId) {
        return resumeDao.loadResume(resumeId);
    }
    @Override
    public boolean updateResume(Resume resume){
        return resumeDao.updateResume(resume);
    }
    @Override
    public void deleteResume(int resumeId){
        resumeDao.deleteAllResume(resumeId);
    }
}
