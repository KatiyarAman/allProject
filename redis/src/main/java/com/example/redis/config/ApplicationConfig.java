package com.example.redis.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Data
@Component
public class ApplicationConfig {
    @Value("${database_driverClassName}")
    private String driver_class;
    @Value("${database_url}")
    private String  db_url;
    @Value("${database_userName}")
    private String db_username;
    @Value("${database_password}")
    private String db_password;

    @Value("${redis.server}")
    private String redisServer;
    @Value("${redis.server.port}")
    private String redisPort;
    @Value("${timeout}")
    private String redisTimeout;

    @Value("${expirationTime}")
    private String experationTime;


}
