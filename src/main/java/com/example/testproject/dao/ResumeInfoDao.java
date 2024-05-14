package com.example.testproject.dao;

import com.example.testproject.entity.ResumeInfo;
import java.util.List;

public interface ResumeInfoDao {
    List<ResumeInfo> findAllForUser(int userId);
}

