package com.example.testproject.dao.impl;

import com.example.testproject.dao.BaseDao;
import com.example.testproject.dao.UserDao;
import com.example.testproject.entity.User;

import java.sql.*;
import java.util.List;
import java.util.Properties;

import java.io.InputStream;

public class UserDaoImpl extends BaseDao<User> implements UserDao {
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String SELECT_LOGIN_PASSWORD = "SELECT password  FROM users WHERE login=?";
    private  static  final  String INSERT_NEW_USER = "INSERT INTO users (login, password) VALUES (?, ?)";
    private static final String UPDATE_USER = "UPDATE users SET login = ?, password = ? WHERE login = ?";
    private static final String INSERT_FILE = "UPDATE users SET file = ? WHERE login = ?";
    private static final String SELECT_FILE = "SELECT file FROM users WHERE login = ?";
    private static UserDaoImpl instance = new UserDaoImpl();
    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean insert(User user) {
        return false;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }
    @Override
    public  boolean register(String login,String password){
        boolean registration=false;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_NEW_USER);
            statement.setString(1, login);
            statement.setString(2, password);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                registration=true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return registration;
    }
    @Override
    public boolean authentificate(String login, String password) {
        boolean match = false;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_LOGIN_PASSWORD);
            statement.setString(1,login);
            ResultSet resultSet = statement.executeQuery();
            String passFromDb;
            if (resultSet.next()) {
                passFromDb=resultSet.getString(1);
                match= password.equals(passFromDb);
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return match;
    }
    @Override
    public boolean updateUser(String newLogin, String newPassword, String currentLogin) {
        boolean update = false;
        Connection connection = null;
        try{
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_USER);
            statement.setString(1, newLogin);
            statement.setString(2, newPassword);
            statement.setString(3, currentLogin);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                update = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            connectionPool.releaseConnection(connection);
        }
        return update;
    }
    //todo
    // загрузка файла в БД
    @Override
    public boolean uploadFile(String login, InputStream fileContent) {
        boolean uploadSuccess = false;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_FILE);
            statement.setBlob(1, fileContent);
            statement.setString(2, login);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                uploadSuccess = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return uploadSuccess;
    }
    @Override
    public byte[] downloadFile(String login) {
        byte[] fileBytes = null;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_FILE);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Blob fileBlob = resultSet.getBlob("file");
                fileBytes = fileBlob.getBytes(1, (int) fileBlob.length());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return fileBytes;
    }
}

