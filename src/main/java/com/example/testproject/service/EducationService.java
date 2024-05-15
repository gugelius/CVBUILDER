package com.example.testproject.service;

import com.example.testproject.entity.Education;
import java.util.List;

public interface EducationService {
    void saveEducation(Education education, int ResumeID);
    List<Education> loadEducations(int resumeId);
    boolean updateEducation(Education education);
    void deleteEducation(int educationId);
    void deleteAllEducation(int resumeId);
}
