package com.example.testproject.command.impl;

import com.example.testproject.command.Command;
import com.example.testproject.entity.Education;
import com.example.testproject.entity.Experience;
import com.example.testproject.entity.Resume;
import com.example.testproject.entity.Skill;
import com.example.testproject.service.EducationService;
import com.example.testproject.service.ExperienceService;
import com.example.testproject.service.ResumeService;
import com.example.testproject.service.SkillService;
import com.example.testproject.service.impl.EducationServiceImpl;
import com.example.testproject.service.impl.ExperienceServiceImpl;
import com.example.testproject.service.impl.ResumeServiceImpl;
import com.example.testproject.service.impl.SkillServiceImpl;
import com.example.testproject.utils.TokenUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class LoadResumeCommand implements Command {
    ResumeService resumeService = ResumeServiceImpl.getInstance();
    ExperienceService experienceService = ExperienceServiceImpl.getInstance();
    EducationService educationService = EducationServiceImpl.getInstance();
    SkillService skillService = SkillServiceImpl.getInstance();
    @Override
    public String execute(HttpServletRequest request, JsonObject jsonObject, HttpServletResponse response) {
        Gson gson = new Gson();
        int resumeId = Integer.parseInt(request.getHeader("ResumeId"));
        String jwt = request.getHeader("Authorization").substring(7);
        if (!TokenUtils.validateJWT(jwt)) {
            return gson.toJson("Invalid token");
        }
        Resume resume = resumeService.loadResume(resumeId);
        List<Experience> experiences = experienceService.loadExperiences(resumeId);
        List<Education> educations = educationService.loadEducations(resumeId);
        List<Skill> skills = skillService.loadSkills(resumeId);
        JsonObject data = new JsonObject();
        data.add("resume", gson.toJsonTree(resume));
        data.add("experiences", gson.toJsonTree(experiences));
        data.add("educations", gson.toJsonTree(educations));
        data.add("skills", gson.toJsonTree(skills));
        return data.toString();
    }

}
