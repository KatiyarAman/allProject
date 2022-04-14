package com.db.multi.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Data
@Component
public class ApplicationConfig {
    @Value("${cms-datasource.driver-class-name}")
    private String driverName;
    @Value("${cms-datasource.jdbc-url}")
    private String url;
    @Value("${cms-datasource.username}")
    private String userName;
    @Value("${cms-datasource.password}")
    private String password;

    @Value("${web_engage_auth}")
    private String web_engage_auth;

    @Value("${web_engage_url}")
    private String web_engage_url;
}
