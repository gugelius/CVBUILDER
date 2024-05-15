package com.example.testproject.dao;

import com.example.testproject.entity.Education;
import java.util.List;

public interface EducationDao {
    boolean save(Education education, int ResumeID);
    List<Education> loadEducations(int resumeId);
    boolean updateEducation(Education education);
    void deleteEducation(int educationId);
    void deleteAllEducation(int resumeId);
}
