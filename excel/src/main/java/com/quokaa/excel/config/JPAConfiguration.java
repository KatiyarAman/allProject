package com.quokaa.excel.config;

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
@EnableJpaRepositories(entityManagerFactoryRef = "factoryExcel",transactionManagerRef = "txnExcel",basePackages = "com.quokaa.excel.repository")
public class JPAConfiguration {

    @Autowired
    ApplicationConfig config;

    @Bean
    public DataSource dataSource(){
        final DriverManagerDataSource dataSource= new DriverManagerDataSource();
        dataSource.setDriverClassName(config.getDriver());
        dataSource.setUrl(config.getUrl());
        dataSource.setUsername(config.getUser());
        dataSource.setPassword(config.getPassword());
        return dataSource;
    }

    @Bean(name = "factoryExcel")
    public LocalContainerEntityManagerFactoryBean factoryExcel(){
        final LocalContainerEntityManagerFactoryBean factoryBean= new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setJpaProperties(additionalProperties());
        final HibernateJpaVendorAdapter hibernateJpaVendorAdapter= new HibernateJpaVendorAdapter();
        factoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        factoryBean.setPackagesToScan("com.quokaa.excel.entity");
        return factoryBean;
    }
    @Bean(name = "txnExcel")
    public PlatformTransactionManager txnExcel(){
        final JpaTransactionManager jpaTransactionManager= new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(factoryExcel().getObject());
        return jpaTransactionManager;
    }

    @Bean(name = "excelJdbc")
    public JdbcTemplate jdbcTemplate(){
        return  new JdbcTemplate(dataSource());
    }

    final Properties additionalProperties() {
        final Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto","update");
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        hibernateProperties.setProperty("hibernate.jdbc.time_zone", "Asia/Kolkata");
      //  hibernateProperties.setProperty("hibernate.show_sql","true");
        return hibernateProperties;
    }


}
