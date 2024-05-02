package com.example.testproject.command.impl;
import com.example.testproject.command.Command;
import com.example.testproject.service.impl.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;

public class FileUploadCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            Part filePart = request.getPart("file"); // Получает загруженный файл
            InputStream inputStream = filePart.getInputStream();
            String login = (String) request.getSession().getAttribute("login"); // Получает логин пользователя из сессии

            UserServiceImpl userService = UserServiceImpl.getInstance();
            boolean uploadSuccess = userService.uploadFile(login, inputStream);

            if (uploadSuccess) {
                // Вернуть страницу, которая будет отображена после успешной загрузки
                return "main.jsp";
            } else {
                // Обработать ошибку
                return "main.jsp";
            }
        } catch (IOException | ServletException e) {
            // Обработать исключение
            return "index.jsp";
        }
    }
}
