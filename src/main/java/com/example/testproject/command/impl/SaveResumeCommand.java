package com.example.testproject.command.impl;

import com.example.testproject.command.Command;
import com.example.testproject.entity.Experience;
import com.example.testproject.entity.Resume;
import com.example.testproject.service.ExperienceService;
import com.example.testproject.service.ResumeService;
import com.example.testproject.service.impl.ExperienceServiceImpl;
import com.example.testproject.service.impl.ResumeServiceImpl;
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

    @Override
    public String execute(HttpServletRequest request, JsonObject jsonObject, HttpServletResponse response) {
        Gson gson = new Gson();
        String jwt = request.getHeader("Authorization").substring(7); // Extract token from "Bearer {token}"
        String userIdString = TokenUtils.decodeJWT(jwt); // Extract username from token
        Resume resume = gson.fromJson(jsonObject, Resume.class);
        System.out.println(jsonObject);
        resume.setUserId(Integer.parseInt(userIdString));
        int resumeId = resumeService.saveResume(resume); // Ensure saveResume returns the ResumeID

        JsonArray experiencesArray = jsonObject.getAsJsonArray("experiences");
        for (JsonElement experienceElement : experiencesArray) {
            Experience experience = gson.fromJson(experienceElement, Experience.class);
            experienceService.saveExperience(experience, resumeId); // Use the returned ResumeID here
        }

        return "Резюме и опыт работы успешно сохранены";
    }
}




