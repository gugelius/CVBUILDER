//package com.example.testproject.command.impl;
//import com.example.testproject.command.Command;
//import com.example.testproject.service.impl.UserServiceImpl;
//
//import com.google.gson.JsonObject;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import jakarta.servlet.http.Part;
//
//import java.io.IOException;
//import java.io.InputStream;
//
////todo тест с форматом файла
//public class FileUploadCommand implements Command {
//    @Override
//    public String execute(JsonObject request, HttpServletResponse response) {
//        try {
//            Part filePart = request.getPart("file"); // Получает загруженный файл
//            if (filePart == null) {
//                System.out.println("Файл не был получен");
//                return "index.jsp";
//            }
//            InputStream inputStream = filePart.getInputStream();
//            if (inputStream == null) {
//                System.out.println("Не удалось получить InputStream файла");
//                return "index.jsp";
//            }
//            String fileExtension = getFileExtension(filePart.getSubmittedFileName()); // Получает расширение файла
//            HttpSession session = request.getSession();
//            String login = (String) session.getAttribute("user");
//            if (login == null) {
//                System.out.println("Логин не найден в сессии");
//                return "index.jsp";
//            }
//            UserServiceImpl userService = UserServiceImpl.getInstance();
//            boolean uploadSuccess = userService.uploadFile(login, inputStream, fileExtension);
//
//            if (uploadSuccess) {
//                request.setAttribute("user","success");
//                return "pages/main.jsp";
//            } else {
//                request.setAttribute("user","failed");
//                return "pages/main.jsp";
//            }
//        } catch (IOException | ServletException e) {
//            System.out.println("Произошла ошибка при обработке файла или запроса");
//            e.printStackTrace();
//            return "index.jsp";
//        }
//    }
//
//    private String getFileExtension(String fileName) {
//        int dotIndex = fileName.lastIndexOf(".");
//        if (dotIndex >= 0) {
//            return fileName.substring(dotIndex + 1);
//        } else {
//            return "";
//        }
//    }
//}
//
//
