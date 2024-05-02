package com.example.testproject.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Deque;

public class ConnectionPool {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/testschema";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "уВшшсс11";
    private static final int MAX_CONNECTIONS = 10;
    private static ConnectionPool instance;
    private final String jdbcUrl;
    private final String username;
    private final String password;
    private final Deque<Connection> connectionPool;
    private final int maxConnections;

    private ConnectionPool(String jdbcUrl, String username, String password, int maxConnections) {
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
        this.maxConnections = maxConnections;
        this.connectionPool = new ArrayDeque<>(maxConnections);
        initializeConnectionPool();
    }

    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool(JDBC_URL,USERNAME,PASSWORD,MAX_CONNECTIONS);
        }
        return instance;
    }

    private void initializeConnectionPool() {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            for (int i = 0; i < maxConnections; i++) {
                Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
                connectionPool.add(connection);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error initializing connection pool", e);
        }
    }

    public synchronized Connection getConnection() {
        if (connectionPool.isEmpty()) {
            throw new IllegalStateException("Connection pool is empty");
        }
        return connectionPool.removeFirst();
    }

    public synchronized void releaseConnection(Connection connection) {
        if (connection != null) {
            connectionPool.addLast(connection);
        }
    }

    public void closeAllConnections() {
        for (Connection connection : connectionPool) {
            try {
                connection.close();
            } catch (SQLException e) {
                // Handle exception (log or rethrow)
            }
        }
    }
}
