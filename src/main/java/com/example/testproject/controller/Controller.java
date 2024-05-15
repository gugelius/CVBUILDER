package com.example.testproject.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.PrintWriter;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.example.testproject.command.Command;
import com.example.testproject.command.CommandType;

@WebServlet(name = "helloServlet", urlPatterns = {"/api/controller","*.do"})
public class Controller extends HttpServlet {

    public void init() {}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        BufferedReader reader = request.getReader();
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
        String commandStr = jsonObject.get("command").getAsString();
        Command command = CommandType.define(commandStr);
        String result = command.execute(request, jsonObject, response);
        PrintWriter out = response.getWriter();
        out.print(result);
        out.flush();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        String commandStr = request.getHeader("command");
        Command command = CommandType.define(commandStr);
        JsonObject jsonObject = new JsonObject();
        String result = command.execute(request, jsonObject, response);
        PrintWriter out = response.getWriter();
        out.print(result);
        out.flush();
    }
    public void destroy() {}
}
