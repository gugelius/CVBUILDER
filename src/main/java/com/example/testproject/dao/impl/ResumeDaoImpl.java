package com.example.testproject.dao.impl;

import com.example.testproject.dao.BaseDao;
import com.example.testproject.dao.ResumeDao;
import com.example.testproject.entity.Resume;
import java.sql.*;
import java.util.List;

public class ResumeDaoImpl extends BaseDao<Resume> implements ResumeDao {
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static ResumeDaoImpl instance = new ResumeDaoImpl();

    private ResumeDaoImpl() {
    }

    public static ResumeDaoImpl getInstance() {
        return instance;
    }
    @Override
    public boolean insert(Resume resume) {
        // Реализуйте здесь логику вставки
        return false;
    }

    @Override
    public boolean delete(Resume resume) {
        // Реализуйте здесь логику удаления
        return false;
    }

    @Override
    public Resume update(Resume resume) {
        // Реализуйте здесь логику обновления
        return null;
    }
    @Override
    public List<Resume> findAll(){
        return null;
    }

    public int save(Resume resume) {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO resumes (UserID, title, date, name, surname, phone, adress, about) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, resume.getUserId());
            statement.setString(2, resume.getTitle());
            statement.setString(3, resume.getDate());
            statement.setString(4, resume.getName());
            statement.setString(5, resume.getSurname());
            statement.setString(6, resume.getPhone());
            statement.setString(7, resume.getAdress());
            statement.setString(8, resume.getAbout());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet rs = statement.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    System.out.println("Резюме успешно сохранено с ID: " + id);
                    return id;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return -1;
    }

}
