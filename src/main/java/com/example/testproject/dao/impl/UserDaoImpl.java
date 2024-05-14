package com.example.testproject.dao.impl;

import com.example.testproject.dao.BaseDao;
import com.example.testproject.dao.UserDao;
import com.example.testproject.entity.User;
import com.example.testproject.entity.UserFile;
import java.sql.*;
import java.util.List;
import java.io.InputStream;

public class UserDaoImpl extends BaseDao<User> implements UserDao {
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String SELECT_LOGIN_PASSWORD = "SELECT password  FROM users WHERE login=?";
    private  static  final  String INSERT_NEW_USER = "INSERT INTO users (login, password) VALUES (?, ?)";
    private static final String UPDATE_USER = "UPDATE users SET login = ?, password = ? WHERE login = ?";
    private static final String INSERT_FILE = "UPDATE users SET file = ?, file_extension = ? WHERE login = ?";
    private static final String SELECT_FILE = "SELECT file, file_extension FROM users WHERE login = ?";
    private static final String SELECT_USER_ID = "SELECT UserID FROM users WHERE login = ?";
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
    public int findUserIdByLogin(String login) {
        int userId = -1;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_USER_ID);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                userId = resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return userId;
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

    //todo тест загрузки в бд, но с форматом
    @Override
    public boolean uploadFile(String login, InputStream fileContent, String fileExtension) {
        boolean uploadSuccess = false;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            if (connection == null) {
                System.out.println("Не удалось установить соединение с базой данных");
                return false;
            }
            PreparedStatement statement = connection.prepareStatement(INSERT_FILE);
            statement.setBlob(1, fileContent);
            statement.setString(2, fileExtension);
            statement.setString(3, login);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                uploadSuccess = true;
            }
        } catch (SQLException throwables) {
            System.out.println("Произошла ошибка при выполнении SQL-запроса");
            throwables.printStackTrace();
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return uploadSuccess;
    }

    //todo с расширением
    @Override
    public UserFile downloadFile(String login) {
        byte[] fileBytes = null;
        String fileExtension = null;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_FILE);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Blob fileBlob = resultSet.getBlob("file");
                if (fileBlob != null) {
                    fileBytes = fileBlob.getBytes(1, (int) fileBlob.length());
                    fileExtension = resultSet.getString("file_extension"); // Получает расширение файла из базы данных
                }
            } else {
                System.out.println("Не удалось найти файл для пользователя: " + login);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return new UserFile(fileBytes, fileExtension); // Возвращает объект UserFile, содержащий байты файла и его расширение
    }
}

