package com.example.testproject.controller;

import java.io.*;

import com.example.testproject.command.Command;
import com.example.testproject.command.CommandType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServlet;
import java.util.Locale;

@WebServlet(name = "helloServlet", urlPatterns = {"/controller","*.do"})
public class Controller extends HttpServlet {


    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
//        String strNum=request.getParameter("num");//берем значение "num"
//        int resNum = 2*Integer.parseInt(strNum);
//        request.setAttribute("result",resNum);

        String commandStr = request.getParameter("command");
        Command command = CommandType.define(commandStr);
        String page = command.execute(request, response);
        request.getRequestDispatcher(page).forward(request, response);
    }

    //todo мой обычный метод doPost
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        //localization
        HttpSession session = request.getSession();
        if (session.getAttribute("jlocale") == null) {
            session.setAttribute("jlocale", "pl");
        }
        String commandStr = request.getParameter("command");
        Command command = CommandType.define(commandStr);
        String page = command.execute(request, response);
        request.getRequestDispatcher(page).forward(request, response);
    }
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html");
//
//        //localization
//        HttpSession session = request.getSession();
//        if(session.getAttribute("jlocale")==null){
//            session.setAttribute("jlocale","pl");
//        }
//
//        String commandStr;
//        if (request.getContentType() != null && request.getContentType().startsWith("multipart/form-data")) {
//            // Для multipart/form-data запросов, используйте getPart()
//            Part commandPart = request.getPart("command");
//            if (commandPart != null) {
//                BufferedReader reader = new BufferedReader(new InputStreamReader(commandPart.getInputStream(), StandardCharsets.UTF_8));
//                commandStr = reader.readLine();
//            } else {
//                // Обработайте ситуацию, когда часть "command" отсутствует в запросе
//                commandStr = null;
//            }
//        } else {
//            // Для всех остальных запросов, используйте getParameter()
//            commandStr = request.getParameter("command");
//        }
//
//        if (commandStr != null) {
//            Command command = CommandType.define(commandStr);
//            String page = command.execute(request, response);
//            request.getRequestDispatcher(page).forward(request, response);
//        } else {
//            // Обработайте ситуацию, когда commandStr все еще null
//        }
//    }

    public void destroy() {
    }
}

