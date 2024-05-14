package com.example.testproject.dao.impl;

import com.example.testproject.dao.BaseDao;
import com.example.testproject.dao.ExperienceDao;
import com.example.testproject.entity.Experience;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ExperienceDaoImpl extends BaseDao<Experience> implements ExperienceDao {
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static ExperienceDaoImpl instance = new ExperienceDaoImpl();

    private ExperienceDaoImpl() {
    }

    public static ExperienceDaoImpl getInstance() {
        return instance;
    }
    @Override
    public boolean insert(Experience experience) {
        // Реализуйте здесь логику вставки
        return false;
    }

    @Override
    public boolean delete(Experience experience) {
        // Реализуйте здесь логику удаления
        return false;
    }

    @Override
    public Experience update(Experience experience) {
        // Реализуйте здесь логику обновления
        return null;
    }
    @Override
    public List<Experience> findAll(){
        return null;
    }
    public boolean save(Experience experience, int ResumeID) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO experience (ResumeID, position, company, city, start, end) VALUES (?, ?, ?, ?, ?, ?)")) {
            preparedStatement.setInt(1, ResumeID);
            preparedStatement.setString(2, experience.getPosition());
            preparedStatement.setString(3, experience.getCompany());
            preparedStatement.setString(4, experience.getCity());
            preparedStatement.setString(5, experience.getStartDate());
            preparedStatement.setString(6, experience.getEndDate());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
