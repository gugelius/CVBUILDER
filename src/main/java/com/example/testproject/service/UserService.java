package com.example.testproject.service;
import com.example.testproject.entity.UserFile;

import java.io.InputStream;

public interface UserService {
    boolean validation(String password);
    boolean authentificate(String login, String password);
    boolean register(String login, String password);
    boolean updateProfile(String login, String oldPassword, String newLogin, String newPassword);
    //todo
    // загрузка файла в БД
    boolean uploadFile(String login, InputStream fileContent, String fileExtension);
    UserFile downloadFile(String login); // Изменено на UserFile
}
