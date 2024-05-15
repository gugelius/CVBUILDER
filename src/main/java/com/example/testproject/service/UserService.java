package com.example.testproject.service;

import com.example.testproject.entity.UserFile;
import java.io.InputStream;

public interface UserService {
    int findUserIdByLogin(String login);
    boolean authentificate(String login, String password);
    boolean register(String login, String password);
    boolean uploadFile(String login, InputStream fileContent, String fileExtension);
    UserFile downloadFile(String login); // Изменено на UserFile
}
