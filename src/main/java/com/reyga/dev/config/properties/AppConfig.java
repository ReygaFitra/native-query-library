package com.reyga.dev.config.properties;

import com.reyga.dev.util.ConnectionUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    ConnectionProperties connectionProperties() {
        return new ConnectionProperties();
    }

    @Bean
    ConnectionUtil connectionUtil() {
        return new ConnectionUtil();
    }
}
