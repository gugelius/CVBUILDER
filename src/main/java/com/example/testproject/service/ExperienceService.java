package com.example.testproject.service;

import com.example.testproject.entity.Experience;
import java.util.List;

public interface ExperienceService {
    void saveExperience(Experience experience,int ResumeID);
    List<Experience> loadExperiences(int resumeId);
    boolean updateExperience(Experience experience);
    void deleteExperience(int experienceId);
    void deleteAllExperience(int resumeId);
}
