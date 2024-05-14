package com.example.testproject.service;

import com.example.testproject.entity.Experience;
import com.example.testproject.entity.Resume;

public interface ExperienceService {
    void saveExperience(Experience experience,int ResumeID);
}
