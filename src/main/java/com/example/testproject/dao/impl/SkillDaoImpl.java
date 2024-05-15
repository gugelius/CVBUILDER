package com.example.testproject.dao.impl;

import com.example.testproject.dao.BaseDao;
import com.example.testproject.dao.SkillDao;
import com.example.testproject.entity.Skill;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SkillDaoImpl extends BaseDao<Skill> implements SkillDao {
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static SkillDaoImpl instance = new SkillDaoImpl();

    private SkillDaoImpl() {
    }

    public static SkillDaoImpl getInstance() {
        return instance;
    }
    @Override
    public boolean insert(Skill skill) {
        return false;
    }

    @Override
    public boolean delete(Skill skill) {
        return false;
    }

    @Override
    public Skill update(Skill skill) {
        return null;
    }
    @Override
    public List<Skill> findAll(){
        return null;
    }
    @Override
    public boolean save(Skill skill, int ResumeID) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO skill (ResumeID, name, level) VALUES (?, ?, ?)")) {
            preparedStatement.setInt(1, ResumeID);
            preparedStatement.setString(2, skill.getName());
            preparedStatement.setInt(3, skill.getLevel());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public List<Skill> loadSkill(int resumeId) {
        List<Skill> skills = new ArrayList<>();
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM skill WHERE ResumeID = ?");
            statement.setInt(1, resumeId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Skill skill = new Skill(
                        rs.getInt("SkillID"),
                        rs.getInt("ResumeID"),
                        false,
                        rs.getString("name"),
                        rs.getInt("level")
                );
                skills.add(skill);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return skills;
    }
    @Override
    public boolean updateSkill(Skill skill) {
        Connection connection = null;
        try {
            skill.setIsNew(false);
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE skill SET name = ?, level = ? WHERE SkillID = ?"
            );
            statement.setString(1, skill.getName());
            statement.setInt(2, skill.getLevel());
            statement.setInt(3, skill.getSkillId());
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
    public void deleteSkill(int skillId) {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM skill WHERE SkillID = ?"
            );
            statement.setInt(1, skillId);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }
    @Override
    public void deleteAllSkill(int resumeId) {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM skill WHERE ResumeID = ?"
            );
            statement.setInt(1, resumeId);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }
}
