package com.example.testproject.dao;

import com.example.testproject.entity.Experience;
import java.util.List;

public interface ExperienceDao {
    boolean save(Experience experience, int ResumeID);
    List<Experience> loadExperience(int resumeId);
    boolean updateExperience(Experience experience);
    void deleteExperience(int experienceId);
    void deleteAllExperience(int resumeId);
}
