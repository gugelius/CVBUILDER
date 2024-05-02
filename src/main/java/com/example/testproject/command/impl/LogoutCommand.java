package com.example.testproject.command.impl;
import com.example.testproject.command.Command;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LogoutCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = "index.jsp";
        return page;
    }
}
