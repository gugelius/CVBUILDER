package com.example.testproject.controller;

import java.io.*;

import com.example.testproject.command.Command;
import com.example.testproject.command.CommandType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

@WebServlet("/fileServlet")
@MultipartConfig
public class FileServlet extends HttpServlet{
    public void init() {

    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String commandStr = request.getParameter("command");
        Command command = CommandType.define(commandStr);
        String page = command.execute(request, response);

        if (page != null && !page.isEmpty()) {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandStr = request.getParameter("command");
        if (commandStr == null) {
            Part commandPart = request.getPart("command");
            if (commandPart != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(commandPart.getInputStream(), StandardCharsets.UTF_8));
                commandStr = reader.readLine();
            }
        }

        if (commandStr != null) {
            Command command = CommandType.define(commandStr);
            String page = command.execute(request, response);
            request.getRequestDispatcher(page).forward(request, response);
        } else {
            // Обработайте ситуацию, когда commandStr все еще null
        }
    }
    public void destroy() {
    }

}

