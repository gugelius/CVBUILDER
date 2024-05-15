package com.example.testproject.command.impl;

import com.example.testproject.command.Command;
import com.example.testproject.entity.ResumeInfo;
import com.example.testproject.service.ResumeInfoService;
import com.example.testproject.service.impl.ResumeInfoServiceImpl;
import com.example.testproject.utils.TokenUtils;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.google.gson.JsonObject;
import java.util.List;

public class LoadResumesCommand implements Command {
    private final Gson gson = new Gson();
    @Override
    public String execute(HttpServletRequest request, JsonObject jsonObject, HttpServletResponse response) {
        String jwt = request.getHeader("Authorization").substring(7);
        if (!TokenUtils.validateJWT(jwt)) {
            return gson.toJson("Invalid token");
        }
        String userIdString = TokenUtils.decodeJWT(jwt);
        ResumeInfoService resumeInfoService = ResumeInfoServiceImpl.getInstance();
        List<ResumeInfo> resumeInfo = resumeInfoService.findAllForUser(Integer.parseInt(userIdString));
        return gson.toJson(resumeInfo);
    }
}
