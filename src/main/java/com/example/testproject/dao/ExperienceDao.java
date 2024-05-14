package com.example.testproject.dao;

import com.example.testproject.entity.Experience;

public interface ExperienceDao {
    boolean save(Experience experience, int ResumeID);
}
