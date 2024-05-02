package com.example.testproject.service.impl;

import com.example.testproject.dao.impl.UserDaoImpl;
import com.example.testproject.service.UserService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.io.InputStream;

public class UserServiceImpl implements UserService {
    private  static final int PAS_LEN = 4;
    private static UserServiceImpl instance = new UserServiceImpl();

    public static UserServiceImpl getInstance() {
        return instance;
    }

    private UserServiceImpl(){

    }

    private String md5(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        }
        catch (NoSuchAlgorithmException e){
            // log warn
            throw new RuntimeException(e);
        }
    }
    @Override
    public boolean validation(String password){
        return password.length()>=PAS_LEN;
    }
    //todo сделать валидацию в пакете утилит
    @Override
    public boolean authentificate(String login, String password) {
            String hashedPassword = md5(password);
            UserDaoImpl userDao = UserDaoImpl.getInstance();
            boolean match = userDao.authentificate(login,hashedPassword);
        return match;
    }

    @Override
    public boolean register(String login, String password){
        String hashedPassword = md5(password);
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        /*для прошлого варианта валидации*/
//        boolean registration = false;
//        if(validation(password)){
//            registration = userDao.register(login,hashedPassword);
//        }
//        return registration;
        boolean registration = userDao.register(login,hashedPassword);
        return registration;
    }
    @Override
    public boolean updateProfile(String login, String oldPassword, String newLogin, String newPassword){
        String hashedOldPassword = md5(oldPassword);
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        boolean match = userDao.authentificate(login, hashedOldPassword);
        if (match) {
            String hashedNewPassword = md5(newPassword);
            boolean update = userDao.updateUser(newLogin, hashedNewPassword, login);
            return update;
        }
        return false;
    }
    //todo
    // загрузка файла в бд
    @Override
    public boolean uploadFile(String login, InputStream fileContent) {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        return userDao.uploadFile(login, fileContent);
    }
    @Override
    public byte[] downloadFile(String login) {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        return userDao.downloadFile(login);
    }
}
