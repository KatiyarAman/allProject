package com.quokka.map.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class MyBatisConfig {

    @Autowired
    ApplicationConfig config;

    @Bean(name="myBatis")
    public DataSource dataSource(){
        final DriverManagerDataSource dataSource= new DriverManagerDataSource();
        dataSource.setDriverClassName(config.getDriver());
        dataSource.setUrl(config.getUrl1());
        dataSource.setUsername(config.getUser());
        dataSource.setPassword(config.getPassword());
        return dataSource;
    }
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        final SqlSessionFactoryBean sqlSessionFactoryBean= new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        return sqlSessionFactoryBean.getObject();
    }
}
