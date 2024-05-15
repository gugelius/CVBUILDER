package com.example.testproject.service;

import com.example.testproject.entity.Resume;

public interface ResumeService {
    int saveResume(Resume resume);
    Resume loadResume(int resumeId);
    boolean updateResume(Resume resume);
    void deleteResume(int resumeId);
}
