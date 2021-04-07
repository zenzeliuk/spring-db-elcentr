package com.elcentr.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Slf4j
public class DatabaseConfig {

    @Bean
    @Profile("prod")
    DataSource getPostgresDataSource() {
        log.info("Try to connect to spring-db-elcentr");
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/spring-db-elcentr");
        hikariConfig.setUsername("postgres");
        hikariConfig.setPassword("1823");
        hikariConfig.setConnectionTimeout(180000);
        hikariConfig.setMaximumPoolSize(100);
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    @Profile("test")
    DataSource getTestH2DataSource() {
        log.info("Try to connect to test-spring-db-elcentr");
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:h2:~/test-spring-db-elcentr");
        hikariConfig.setUsername("max");
        hikariConfig.setPassword("1823");
        hikariConfig.setConnectionTimeout(180000);
        hikariConfig.setMaximumPoolSize(100);
        hikariConfig.setDriverClassName("org.h2.Driver");
        return new HikariDataSource(hikariConfig);
    }

}

