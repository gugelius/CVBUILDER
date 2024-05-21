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
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class UpdateResumeCommand implements Command {
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
        resume.setResumeId(Integer.parseInt(request.getHeader("ResumeId")));
        System.out.println(resumeService.updateResume(resume));
        JsonArray experiencesArray = jsonObject.getAsJsonArray("experiences");
        List<Experience> currentExperiences = experienceService.loadExperiences(resume.getResumeId());
        JsonArray educationsArray = jsonObject.getAsJsonArray("educations");
        List<Education> currentEducations = educationService.loadEducations(resume.getResumeId());
        JsonArray skillsArray = jsonObject.getAsJsonArray("skills");
        List<Skill> currentSkills = skillService.loadSkills(resume.getResumeId());
        if (!TokenUtils.validateJWT(jwt)) {
            return gson.toJson("Invalid token");
        }
        for (Experience currentExperience : currentExperiences) {
            boolean found = false;
            for (JsonElement experienceElement : experiencesArray) {
                Experience newExperience = gson.fromJson(experienceElement, Experience.class);
                if (newExperience.getExperienceId() == (currentExperience.getExperienceId())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                experienceService.deleteExperience(currentExperience.getExperienceId());
            }
        }
        for (Education currentEducation : currentEducations) {
            boolean found = false;
            for (JsonElement educationElement : educationsArray) {
                Education newEducation = gson.fromJson(educationElement, Education.class);
                if (newEducation.getEducationId() == (currentEducation.getEducationId())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                educationService.deleteEducation(currentEducation.getEducationId());
            }
        }
        for (Skill currentSkill : currentSkills) {
            boolean found = false;
            for (JsonElement skillElement : skillsArray) {
                Skill newSkill = gson.fromJson(skillElement, Skill.class);
                if (newSkill.getSkillId() == (currentSkill.getSkillId())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                skillService.deleteSkill(currentSkill.getSkillId());
            }
        }

        for (JsonElement experienceElement : experiencesArray) {
            Experience experience = gson.fromJson(experienceElement, Experience.class);
            if(experience.getIsNew()) {
                experienceService.saveExperience(experience,resume.getResumeId());
            }
            else {
                experienceService.updateExperience(experience);
            }
        }
        for (JsonElement educationElement : educationsArray) {
            Education education = gson.fromJson(educationElement, Education.class);
            if(education.getIsNew()) {
                educationService.saveEducation(education,resume.getResumeId());
            }
            else {
                educationService.updateEducation(education);
            }
        }
        for (JsonElement skillElement : skillsArray) {
            Skill skill = gson.fromJson(skillElement, Skill.class);
            if(skill.getIsNew()) {
                skillService.saveSkill(skill,resume.getResumeId());
            }
            else {
                skillService.updateSkill(skill);
            }
        }

        return gson.toJson("success");
    }
}
