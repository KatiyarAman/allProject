package com.quokaa.excel.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Data
@Component
public class ApplicationConfig {

    @Value("${db-driver}")
    private String driver;

    @Value("${db-url}")
    private String url;

    @Value("${db-user}")
    private String user;

    @Value("${db-password}")
    private String password;

    @Value("${root-dir}")
    private String rootDir;

    @Value("${db-url1}")
    private String url1;

    @Value("${db-url_cms}")
    private String cms_url;
}
