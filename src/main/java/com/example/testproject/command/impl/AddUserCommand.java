package com.example.testproject.command.impl;

import com.example.testproject.command.Command;
import com.example.testproject.service.UserService;
import com.example.testproject.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class AddUserCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, JsonObject jsonObject, HttpServletResponse response) {
        Gson gson = new Gson();
        String login = jsonObject.get("username").getAsString();
        String password = jsonObject.get("pass").getAsString();
        UserService userService = UserServiceImpl.getInstance();
        String page;
        if(userService.validation(password)){
            if(userService.register(login,password)) {
                //request.setAttribute("user", login);
                String jwt = userService.createJWT(login, "YourApp", login, 3600000); // 1 hour ttl
                JsonObject responseJson = new JsonObject();
                responseJson.addProperty("message", "User authenticated");
                responseJson.addProperty("token", jwt);
                return gson.toJson(responseJson);
            }else{
                return gson.toJson("Incorrect login or pass");
            }
        }else {
            return gson.toJson("short password");
        }
    }
}

