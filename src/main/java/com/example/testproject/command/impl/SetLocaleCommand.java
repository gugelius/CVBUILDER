package com.example.testproject.command.impl;
import com.example.testproject.command.Command;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;

public class SetLocaleCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String locale = request.getParameter("locale");
        HttpSession session = request.getSession();
        session.setAttribute("jlocale", locale);
        return "/index.jsp";
    }
}


