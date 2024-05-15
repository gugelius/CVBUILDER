package com.example.testproject.service;

import com.example.testproject.entity.ResumeInfo;
import java.util.List;

public interface ResumeInfoService {
    List<ResumeInfo> findAllForUser(int userId);
}

