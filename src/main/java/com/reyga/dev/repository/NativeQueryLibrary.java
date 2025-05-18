package com.reyga.dev.repository;

import com.reyga.dev.util.ConnectionUtil;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;

public class NativeQueryLibrary {

    private final ConnectionUtil connectionUtil;

    public NativeQueryLibrary(ConnectionUtil connectionUtil) {
        this.connectionUtil = connectionUtil;
    }

    /**
     * Sample Native Query Usage
     */
    public void sampleInsertData(String values1, String values2){
        try (Connection connection = connectionUtil.getDataSource().getConnection()) {
            String sql = "INSERT INTO comments(email, comment) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1,values1);
                statement.setString(2,values2);
                statement.executeUpdate();

                try (ResultSet resultSet = statement.getResultSet()) {
                    if (resultSet.next()) {
                        // your action
                    }
                }
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public Statement constructStatement(HikariDataSource dataSource) {
        Connection connection;
        try {
            if (dataSource == null) {
                connection = connectionUtil.getDataSource().getConnection();
            } else {
                connection = dataSource.getConnection();
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }

        try (connection) {
            try (Statement statement = connection.createStatement()) {
                return statement;
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public PreparedStatement constructPreparedStatement(HikariDataSource dataSource, String sql) {
        Connection connection;
        try {
            if (dataSource == null) {
                connection = connectionUtil.getDataSource().getConnection();
            } else {
                connection = dataSource.getConnection();
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }

        try (connection) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                return statement;
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public ResultSet constructResultSetExecQuery(PreparedStatement statement) {
        try (ResultSet resultSet = statement.executeQuery()) {
            return resultSet;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public ResultSet constructResultSetExecQuery(Statement statement, String sql) {
        try (ResultSet resultSet = statement.executeQuery(sql)) {
            return resultSet;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public ResultSet constructResultSetExecUpdate(PreparedStatement statement) {
        try (ResultSet resultSet = statement.executeQuery()) {
            return resultSet;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public ResultSet constructResultSetExecUpdate(Statement statement, String sql) {
        try (ResultSet resultSet = statement.executeQuery(sql)) {
            return resultSet;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}
