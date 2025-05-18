package com.reyga.dev.repository;

import com.reyga.dev.util.ConnectionUtil;

import java.sql.*;

public class NativeQueryLibrary {

    private final ConnectionUtil connectionUtil;

    public NativeQueryLibrary(ConnectionUtil connectionUtil) {
        this.connectionUtil = connectionUtil;
    }

    public void insert(String tableName, String values1, String values2){
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
}
