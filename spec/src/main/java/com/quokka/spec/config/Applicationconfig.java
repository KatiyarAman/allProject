package com.quokka.spec.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
@Data
@Configuration
public class Applicationconfig {

	@Value("${db-driver}")
	private String sqlDriver;
	@Value("${db-url}")
	private String sqlUrl;
	@Value("${db-user}")
	private String sqlUsername;
	@Value("${db-password}")
	private String sqlPassword;
}
