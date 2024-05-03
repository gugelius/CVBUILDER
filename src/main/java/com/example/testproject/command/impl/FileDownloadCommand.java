package com.example.testproject.command.impl;
import com.example.testproject.command.Command;
import com.example.testproject.entity.UserFile;
import com.example.testproject.service.impl.UserServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//todo test с форматом
public class FileDownloadCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String login = (String) request.getSession().getAttribute("user"); // Получает логин пользователя из сессии

            if (login == null || login.isEmpty()) {
                request.setAttribute("errorMessage", "Логин пользователя не найден в сессии");
                return "pages/main.jsp";
            }

            UserServiceImpl userService = UserServiceImpl.getInstance();
            UserFile userFile = userService.downloadFile(login); // Получает объект UserFile, содержащий байты файла и его расширение

            if (userFile == null || userFile.getFileBytes().length == 0) {
                request.setAttribute("errorMessage", "Файл не найден");
                return "pages/main.jsp";
            }

            // Проверка расширения файла
            String fileExtension = userFile.getFileExtension();
            if (fileExtension == null || fileExtension.isEmpty()) {
                System.out.println("Расширение файла не найдено");
            } else {
                System.out.println("Расширение файла: " + fileExtension);
            }

            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + login + "." + fileExtension + "\""); // Использует расширение файла при установке заголовка Content-Disposition

            // Отправьте файл обратно клиенту
            response.getOutputStream().write(userFile.getFileBytes());
        } catch (IOException e) {
            // Обработать исключение
            request.setAttribute("errorMessage", "Произошла ошибка при чтении файла");
            return "pages/main.jsp";
        }
        return null;
    }
}

