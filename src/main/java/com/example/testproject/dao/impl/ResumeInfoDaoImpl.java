package com.example.testproject.dao.impl;

import com.example.testproject.dao.BaseDao;
import com.example.testproject.dao.ResumeInfoDao;
import com.example.testproject.entity.ResumeInfo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResumeInfoDaoImpl extends BaseDao<ResumeInfo> implements ResumeInfoDao {
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String SELECT_ALL_RESUMES = "SELECT * FROM resumes";
    private static ResumeInfoDaoImpl instance = new ResumeInfoDaoImpl();

    private ResumeInfoDaoImpl() {
    }

    public static ResumeInfoDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean insert(ResumeInfo resumeInfo) {
        // Реализуйте здесь логику вставки
        return false;
    }

    @Override
    public boolean delete(ResumeInfo resumeInfo) {
        // Реализуйте здесь логику удаления
        return false;
    }

    @Override
    public ResumeInfo update(ResumeInfo resumeInfo) {
        // Реализуйте здесь логику обновления
        return null;
    }
    @Override
    public  List<ResumeInfo> findAll(){
        return null;
    }
    @Override
    public List<ResumeInfo> findAllForUser(int userId) {
        List<ResumeInfo> resumeInfos = new ArrayList<>();
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_RESUMES + " WHERE UserID = ?");
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ResumeInfo resumeInfo = new ResumeInfo(
                        resultSet.getInt("ResumeID"),
                        resultSet.getString("title"),
                        resultSet.getString("date")
                );
                resumeInfos.add(resumeInfo);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return resumeInfos;
    }


    // Implement other methods as needed...
}
