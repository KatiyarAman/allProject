package com.quokka.spec.config;

import java.sql.DriverManager;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DBConfig {
	@Autowired
	private Applicationconfig config;
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource= new DriverManagerDataSource();
		dataSource.setDriverClassName(config.getSqlDriver());
		dataSource.setUrl(config.getSqlUrl());
		dataSource.setUsername(config.getSqlUsername());
		dataSource.setPassword(config.getSqlPassword());
		return dataSource;
	}
	@Bean 
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}

}
