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
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class SaveResumeCommand implements Command {
    ResumeService resumeService = ResumeServiceImpl.getInstance();
    ExperienceService experienceService = ExperienceServiceImpl.getInstance();
    EducationService educationService = EducationServiceImpl.getInstance();
    SkillService skillService = SkillServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, JsonObject jsonObject, HttpServletResponse response) {
        Gson gson = new Gson();
        String jwt = request.getHeader("Authorization").substring(7);
        String userIdString = TokenUtils.decodeJWT(jwt);
        Resume resume = gson.fromJson(jsonObject, Resume.class);
        resume.setUserId(Integer.parseInt(userIdString));
        int resumeId = resumeService.saveResume(resume);

        if (!TokenUtils.validateJWT(jwt)) {
            return gson.toJson("Invalid token");
        }
        JsonArray experiencesArray = jsonObject.getAsJsonArray("experiences");
        for (JsonElement experienceElement : experiencesArray) {
            Experience experience = gson.fromJson(experienceElement, Experience.class);
            experienceService.saveExperience(experience, resumeId);
        }
        JsonArray educationArray = jsonObject.getAsJsonArray("educations");
        for (JsonElement educationElement : educationArray) {
            Education education = gson.fromJson(educationElement, Education.class);
            educationService.saveEducation(education, resumeId);
        }
        JsonArray skillArray = jsonObject.getAsJsonArray("skills");
        for (JsonElement skillElement : skillArray) {
            Skill skill = gson.fromJson(skillElement, Skill.class);
            skillService.saveSkill(skill, resumeId);
        }

        return gson.toJson("success");
    }
}




