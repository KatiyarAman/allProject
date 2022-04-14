package com.db.multi.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
public class DBConfig {


    @Bean(name = "csvDatasource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource csvDataSource() {
        return DataSourceBuilder.create().build();
    }
    
    @Bean(name = "csvTransactionManager")
    @Autowired
    public DataSourceTransactionManager csvTransactionManager(@Qualifier("csvDatasource") DataSource csvDatasource) {
        DataSourceTransactionManager csvTransactionManager = new DataSourceTransactionManager(csvDatasource);
        return csvTransactionManager;
    }
    @Bean(name="csvJdbcTemplate")
    public JdbcTemplate csvJdbcTemplate(){
        return new JdbcTemplate(csvDataSource());
    }

}
