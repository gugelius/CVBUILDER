package com.example.testproject.command.impl;
import com.example.testproject.command.Command;
import com.example.testproject.service.impl.UserServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class FileDownloadCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String login = (String) request.getSession().getAttribute("login"); // Получает логин пользователя из сессии

            UserServiceImpl userService = UserServiceImpl.getInstance();
            byte[] fileBytes = userService.downloadFile(login);

            if (fileBytes != null) {
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + login + ".file\"");

                // Отправьте файл обратно клиенту
                response.getOutputStream().write(fileBytes);
            } else {
                // Обработать ошибку
                request.setAttribute("errorMessage", "Файл не найден");
                return "pages/main.jsp";
            }
        } catch (IOException e) {
            // Обработать исключение
            request.setAttribute("errorMessage", "Произошла ошибка при чтении файла");
            return "pages/main.jsp";
        }
        return null;
    }

}
