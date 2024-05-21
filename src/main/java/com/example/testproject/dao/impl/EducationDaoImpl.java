package com.example.testproject.dao.impl;

import com.example.testproject.dao.BaseDao;
import com.example.testproject.dao.EducationDao;
import com.example.testproject.entity.Education;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EducationDaoImpl extends BaseDao<Education> implements EducationDao {
    private static final String SAVE_EDUCATION = "INSERT INTO education (ResumeID, name, degree, city, start, end) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String LOAD_EDUCATIONS = "SELECT * FROM education WHERE ResumeID = ?";
    private static final String UPDATE_EDUCATION = "UPDATE education SET name = ?, degree = ?, city = ?, start = ?, end = ? WHERE EducationID = ?";
    private static final String DELETE_EDUCATION = "DELETE FROM education WHERE EducationID = ?";
    private static final String DELETE_ALL_EDUCATION = "DELETE FROM education WHERE ResumeID = ?";
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static EducationDaoImpl instance = new EducationDaoImpl();

    private EducationDaoImpl() {
    }

    public static EducationDaoImpl getInstance() {
        return instance;
    }
    @Override
    public boolean insert(Education education) {
        return false;
    }

    @Override
    public boolean delete(Education education) {
        return false;
    }

    @Override
    public Education update(Education education) {
        return null;
    }
    @Override
    public List<Education> findAll(){
        return null;
    }

    @Override
    public boolean save(Education education, int ResumeID) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_EDUCATION)) {
            preparedStatement.setInt(1, ResumeID);
            preparedStatement.setString(2, education.getName());
            preparedStatement.setString(3, education.getDegree());
            preparedStatement.setString(4, education.getCity());
            preparedStatement.setString(5, education.getStartYear());
            preparedStatement.setString(6, education.getEndYear());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public List<Education> loadEducations(int resumeId) {
        List<Education> educations = new ArrayList<>();
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(LOAD_EDUCATIONS);
            statement.setInt(1, resumeId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Education education = new Education(
                        rs.getInt("EducationID"),
                        rs.getInt("ResumeID"),
                        false,
                        rs.getString("name"),
                        rs.getString("degree"),
                        rs.getString("city"),
                        rs.getString("start"),
                        rs.getString("end")
                );
                educations.add(education);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return educations;
    }
    @Override
    public boolean updateEducation(Education education) {
        Connection connection = null;
        try {
            education.setIsNew(false);
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_EDUCATION);
            statement.setString(1, education.getName());
            statement.setString(2, education.getDegree());
            statement.setString(3,education.getCity());
            statement.setString(4, education.getStartYear());
            statement.setString(5, education.getEndYear());
            statement.setInt(6, education.getEducationId());
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
    public void deleteEducation(int educationId) {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_EDUCATION);
            statement.setInt(1, educationId);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }
    @Override
    public void deleteAllEducation(int resumeId) {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_ALL_EDUCATION);
            statement.setInt(1, resumeId);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }
}
