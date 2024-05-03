package com.example.testproject.command.impl;
import com.example.testproject.command.Command;
import com.example.testproject.service.impl.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;

//public class FileUploadCommand implements Command {
//    @Override
//    public String execute(HttpServletRequest request, HttpServletResponse response) {
//        try {
//            Part filePart = request.getPart("file"); // Получает загруженный файл
//            InputStream inputStream = filePart.getInputStream();
////            String login = (String) request.getSession().getAttribute("user"); // Получает логин пользователя из сессии
//            HttpSession session = request.getSession();
//            String login = (String) session.getAttribute("user");
//
//            UserServiceImpl userService = UserServiceImpl.getInstance();
//            boolean uploadSuccess = userService.uploadFile(login, inputStream);
//
//            if (uploadSuccess) {
//                // Вернуть страницу, которая будет отображена после успешной загрузки
//                request.setAttribute("user","success");
//                return "pages/main.jsp";
//            } else {
//                // Обработать ошибку
//                request.setAttribute("user","failed");
//                return "pages/main.jsp";
//            }
//        } catch (IOException | ServletException e) {
//            // Обработать исключение
//            return "index.jsp";
//        }
//    }
//}
public class FileUploadCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            Part filePart = request.getPart("file"); // Получает загруженный файл
            if (filePart == null) {
                System.out.println("Файл не был получен");
                return "index.jsp";
            }
            InputStream inputStream = filePart.getInputStream();
            if (inputStream == null) {
                System.out.println("Не удалось получить InputStream файла");
                return "index.jsp";
            }
            HttpSession session = request.getSession();
            String login = (String) session.getAttribute("user");
            if (login == null) {
                System.out.println("Логин не найден в сессии");
                return "index.jsp";
            }
            UserServiceImpl userService = UserServiceImpl.getInstance();
            boolean uploadSuccess = userService.uploadFile(login, inputStream);

            if (uploadSuccess) {
                request.setAttribute("user","success");
                return "pages/main.jsp";
            } else {
                request.setAttribute("user","failed");
                return "pages/main.jsp";
            }
        } catch (IOException | ServletException e) {
            System.out.println("Произошла ошибка при обработке файла или запроса");
            e.printStackTrace();
            return "index.jsp";
        }
    }
}

