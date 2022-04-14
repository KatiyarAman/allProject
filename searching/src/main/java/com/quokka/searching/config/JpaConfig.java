package com.quokka.searching.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "searchFactory",transactionManagerRef = "searchTxn",basePackages = {"com.quokka.searching.repository"})
public class JpaConfig {

    @Autowired
    private ApplicationConfig config;

    @Bean
    public DataSource dataSource(){
        final DriverManagerDataSource dataSource= new DriverManagerDataSource();
        dataSource.setDriverClassName(config.getDriver());
        dataSource.setUrl(config.getUrl());
        dataSource.setUsername(config.getUsername());
        dataSource.setPassword(config.getPassword());
        return  dataSource;
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean searchFactory(){
        final LocalContainerEntityManagerFactoryBean factoryBean= new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan("com.quokka.searching.entity");
        final HibernateJpaVendorAdapter jpaVendorAdapter= new HibernateJpaVendorAdapter();
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        factoryBean.setJpaProperties(additionalProperties());
        return factoryBean;
    }
    @Bean
    public PlatformTransactionManager searchTxn(){
        final JpaTransactionManager transactionManager=new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(searchFactory().getObject());
        return transactionManager;
    }
    final Properties additionalProperties() {
        final Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto","update");
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        hibernateProperties.setProperty("hibernate.jdbc.time_zone", "Asia/Kolkata");
        hibernateProperties.setProperty("hibernate.show_sql","true");

        //hibernateProperties.setProperty("hibernate.show_sql","yes");
        return hibernateProperties;
    }
    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }
}
