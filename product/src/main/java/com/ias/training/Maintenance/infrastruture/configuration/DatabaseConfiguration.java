package com.ias.training.Maintenance.infrastruture.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
@Configuration
public class DatabaseConfiguration {

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/maintenace_db");
        config.setUsername("postgres_user");
        config.setPassword("postgres_password");
        return new HikariDataSource(config);
    }
}
