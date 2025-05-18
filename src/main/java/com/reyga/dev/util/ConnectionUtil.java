package com.reyga.dev.util;

import com.reyga.dev.config.properties.ConnectionProperties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;

public class ConnectionUtil {

    @Autowired
    private ConnectionProperties connectionProperties;

    public HikariDataSource getDataSource() {

        HikariConfig config = new HikariConfig();
        config.setDriverClassName(connectionProperties.getDriverClassName());
        config.setJdbcUrl(connectionProperties.getJdbcUrl());
        config.setUsername(connectionProperties.getUsername());
        config.setPassword(connectionProperties.getPassword());

        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        config.setIdleTimeout(60_000);
        config.setMaxLifetime(10 * 60_000);

        return new HikariDataSource(config);
    }
}
