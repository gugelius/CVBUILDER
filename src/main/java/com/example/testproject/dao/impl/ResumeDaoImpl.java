package com.example.testproject.dao.impl;

import com.example.testproject.dao.BaseDao;
import com.example.testproject.dao.ResumeDao;
import com.example.testproject.entity.Resume;
import java.sql.*;
import java.util.List;

public class ResumeDaoImpl extends BaseDao<Resume> implements ResumeDao {
    private static final String SAVE_RESUME = "INSERT INTO resumes (UserID, title, date, name, surname, email, phone, adress, about) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String LOAD_RESUME = "SELECT * FROM resumes WHERE ResumeID = ?";
    private static final String UPDATE_RESUME = "UPDATE resumes SET title = ?, date = ?, name = ?, surname = ?, email = ?, phone = ?, adress = ?, about = ? WHERE ResumeID = ?";
    private static final String DELETE_RESUME = "DELETE FROM resumes WHERE ResumeID = ?";
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static ResumeDaoImpl instance = new ResumeDaoImpl();

    private ResumeDaoImpl() {
    }

    public static ResumeDaoImpl getInstance() {
        return instance;
    }
    @Override
    public boolean insert(Resume resume) {
        return false;
    }

    @Override
    public boolean delete(Resume resume) {
        return false;
    }

    @Override
    public Resume update(Resume resume) {
        return null;
    }
    @Override
    public List<Resume> findAll(){
        return null;
    }

    @Override
    public int save(Resume resume) {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SAVE_RESUME, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, resume.getUserId());
            statement.setString(2, resume.getTitle());
            statement.setString(3, resume.getDate());
            statement.setString(4, resume.getName());
            statement.setString(5, resume.getSurname());
            statement.setString(6,resume.getEmail());
            statement.setString(7, resume.getPhone());
            statement.setString(8, resume.getAdress());
            statement.setString(9, resume.getAbout());
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
    @Override
    public Resume loadResume(int resumeId) {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(LOAD_RESUME);
            statement.setInt(1, resumeId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Resume resume = new Resume(
                        rs.getInt("ResumeID"),
                        rs.getInt("UserID"),
                        rs.getString("title"),
                        rs.getString("date"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("adress"),
                        rs.getString("about")
                );
                return resume;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return null;
    }
    @Override
    public boolean updateResume(Resume resume) {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_RESUME);
            statement.setString(1, resume.getTitle());
            statement.setString(2, resume.getDate());
            statement.setString(3, resume.getName());
            statement.setString(4, resume.getSurname());
            statement.setString(5, resume.getEmail());
            statement.setString(6, resume.getPhone());
            statement.setString(7, resume.getAdress());
            statement.setString(8, resume.getAbout());
            statement.setInt(9, resume.getResumeId());
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
    public void deleteAllResume(int resumeId){
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_RESUME);
            statement.setInt(1, resumeId);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }
}
