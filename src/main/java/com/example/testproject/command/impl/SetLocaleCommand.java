//package com.example.testproject.command.impl;
//import com.example.testproject.command.Command;
//import com.google.gson.JsonObject;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//public class SetLocaleCommand implements Command {
//    @Override
//    public String execute(JsonObject request, HttpServletResponse response) {
//        String locale = request.getParameter("locale");
//        HttpSession session = request.getSession();
//        session.setAttribute("jlocale", locale);
//        return "/index.jsp";
//    }
//}


