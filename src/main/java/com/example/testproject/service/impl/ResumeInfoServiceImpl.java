package com.example.testproject.service.impl;

import com.example.testproject.dao.ResumeInfoDao;
import com.example.testproject.dao.impl.ResumeInfoDaoImpl;
import com.example.testproject.entity.ResumeInfo;
import com.example.testproject.service.ResumeInfoService;
import java.util.List;

public class ResumeInfoServiceImpl implements ResumeInfoService {
    private static ResumeInfoServiceImpl instance = new ResumeInfoServiceImpl();
    private final ResumeInfoDao resumeInfoDao = ResumeInfoDaoImpl.getInstance();
    public static ResumeInfoServiceImpl getInstance() {
        return instance;
    }
    private ResumeInfoServiceImpl() {
    }
    @Override
    public List<ResumeInfo> findAllForUser(int UserId) {
        return resumeInfoDao.findAllForUser(UserId);
    }
}
