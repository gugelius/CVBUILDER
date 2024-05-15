package com.example.testproject.command.impl;

import com.example.testproject.command.Command;
import com.example.testproject.service.*;
import com.example.testproject.service.impl.*;
import com.example.testproject.utils.TokenUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class DeleteResumeCommand implements Command {
    ResumeService resumeService = ResumeServiceImpl.getInstance();
    ExperienceService experienceService = ExperienceServiceImpl.getInstance();
    EducationService educationService = EducationServiceImpl.getInstance();
    SkillService skillService = SkillServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, JsonObject jsonObject, HttpServletResponse response) {
        Gson gson = new Gson();
        String jwt = request.getHeader("Authorization").substring(7);
        if (!TokenUtils.validateJWT(jwt)) {
            return gson.toJson("Invalid token");
        }
        int resumeId = Integer.parseInt(request.getHeader("ResumeId"));
        experienceService.deleteAllExperience(resumeId);
        educationService.deleteAllEducation(resumeId);
        skillService.deleteAllSkill(resumeId);
        resumeService.deleteResume(resumeId);
        return gson.toJson("success");
    }
}



