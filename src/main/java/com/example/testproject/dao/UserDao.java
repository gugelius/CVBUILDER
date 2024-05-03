package com.example.testproject.dao;
import com.example.testproject.entity.UserFile;

import java.io.InputStream;

public interface UserDao {
    boolean authentificate(String login, String  password);
    boolean register(String login, String password);
    boolean updateUser(String newLogin, String newPassword, String currentLogin);
    //todo
    // загрузка файла
    boolean uploadFile(String login, InputStream fileContent, String fileExtension);
    UserFile downloadFile(String login); // Изменено на UserFile
}
