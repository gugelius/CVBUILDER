package com.example.testproject.dao.impl;

import com.example.testproject.dao.BaseDao;
import com.example.testproject.dao.ExperienceDao;
import com.example.testproject.entity.Experience;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExperienceDaoImpl extends BaseDao<Experience> implements ExperienceDao {
    private static final String SAVE_EXPERIENCE = "INSERT INTO experience (ResumeID, position, company, city, start, end) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String LOAD_EXPERIENCE = "SELECT * FROM experience WHERE ResumeID = ?";
    private static final String UPDATE_EXPERIENCE = "UPDATE experience SET position = ?, company = ?, city = ?, start = ?, end = ? WHERE ExperienceID = ?";
    private static final String DELETE_EXPERIENCE = "DELETE FROM experience WHERE ExperienceID = ?";
    private static final String DELETE_ALL_EXPERIENCE = "DELETE FROM experience WHERE ResumeID = ?";
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static ExperienceDaoImpl instance = new ExperienceDaoImpl();

    private ExperienceDaoImpl() {
    }

    public static ExperienceDaoImpl getInstance() {
        return instance;
    }
    @Override
    public boolean insert(Experience experience) {
        return false;
    }

    @Override
    public boolean delete(Experience experience) {
        return false;
    }

    @Override
    public Experience update(Experience experience) {
        return null;
    }
    @Override
    public List<Experience> findAll(){
        return null;
    }

    @Override
    public boolean save(Experience experience, int ResumeID) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_EXPERIENCE)) {
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
    @Override
    public List<Experience> loadExperience(int resumeId) {
        List<Experience> experiences = new ArrayList<>();
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(LOAD_EXPERIENCE);
            statement.setInt(1, resumeId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Experience experience = new Experience(
                        rs.getInt("ExperienceID"),
                        rs.getInt("ResumeID"),
                        false,
                        rs.getString("position"),
                        rs.getString("company"),
                        rs.getString("city"),
                        rs.getString("start"),
                        rs.getString("end")
                );
                experiences.add(experience);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return experiences;
    }
    @Override
    public boolean updateExperience(Experience experience) {
        Connection connection = null;
        try {
            experience.setIsNew(false);
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_EXPERIENCE);
            statement.setString(1, experience.getPosition());
            statement.setString(2, experience.getCompany());
            statement.setString(3,experience.getCity());
            statement.setString(4, experience.getStartDate());
            statement.setString(5, experience.getEndDate());
            statement.setInt(6, experience.getExperienceId());
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return false;
    }
    @Override
    public void deleteExperience(int experienceId) {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_EXPERIENCE);
            statement.setInt(1, experienceId);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }
    @Override
    public void deleteAllExperience(int resumeId) {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_ALL_EXPERIENCE);
            statement.setInt(1, resumeId);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }
}
