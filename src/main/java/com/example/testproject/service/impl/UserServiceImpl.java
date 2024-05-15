package com.example.testproject.service.impl;

import com.example.testproject.utils.ValidationUtils;
import com.example.testproject.dao.impl.UserDaoImpl;
import com.example.testproject.entity.UserFile;
import com.example.testproject.service.UserService;
import java.io.InputStream;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl instance = new UserServiceImpl();
    public static UserServiceImpl getInstance() {
        return instance;
    }
    private UserServiceImpl(){
    }
    @Override
    public int findUserIdByLogin(String login){
        UserDaoImpl userDao= UserDaoImpl.getInstance();
        return userDao.findUserIdByLogin(login);
    }
    @Override
    public boolean authentificate(String login, String password) {
            String hashedPassword = ValidationUtils.md5(password);
            UserDaoImpl userDao = UserDaoImpl.getInstance();
            boolean match = userDao.authentificate(login,hashedPassword);
        return match;
    }
    @Override
    public boolean register(String login, String password){
        String hashedPassword = ValidationUtils.md5(password);
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        boolean registration = userDao.register(login,hashedPassword);
        return registration;
    }
    @Override
    public boolean uploadFile(String login, InputStream fileContent, String fileExtension) {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        return userDao.uploadFile(login, fileContent, fileExtension);
    }
    @Override
    public UserFile downloadFile(String login) { // Изменено на UserFile
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        return userDao.downloadFile(login);
    }
}
