package com.example.testproject.dao;

import com.example.testproject.entity.Resume;

public interface ResumeDao {
    int save(Resume resume);
    Resume loadResume(int resumeId);
    boolean updateResume(Resume resume);
    void deleteAllResume(int resumeId);
}
