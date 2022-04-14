package com.quokka.searching.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Data
@Component
public class ApplicationConfig {
@Value("${redis.server}")
    private String redisServer;
    @Value("${redis.server.port}")
    private String redisPort;
    @Value("${timeout}")
    private String redisTimeout;

    @Value("${expirationTime}")
    private String experationTime;

    @Value("${database_driverClassName}")
    private String driver;
    @Value("${database_url}")
    private String url;
    @Value("${database_userName}")
    private String username;
    @Value("${database_password}")
    private String password;
}
