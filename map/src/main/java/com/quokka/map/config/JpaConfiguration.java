package com.quokka.map.config;

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
@EnableJpaRepositories(entityManagerFactoryRef = "answerFactory",transactionManagerRef = "answerTransactionManager",basePackages = {"com.quokka.map.doa"})
public class JpaConfiguration {
    @Autowired
    ApplicationConfig applicationConfig;

    @Bean
    public DataSource dataSource(){
       final DriverManagerDataSource dataSource= new DriverManagerDataSource();
        dataSource.setDriverClassName(applicationConfig.getDriver());
        dataSource.setUrl(applicationConfig.getUrl());
        dataSource.setUsername(applicationConfig.getUser());
        dataSource.setPassword(applicationConfig.getPassword());
   return dataSource;
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean answerFactory(){
        final LocalContainerEntityManagerFactoryBean f= new LocalContainerEntityManagerFactoryBean();
        f.setDataSource(dataSource());
       // f.setJpaProperties(additionalProperties());
        f.setPackagesToScan("com.quokka.map.domain");
        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        f.setJpaVendorAdapter(vendorAdapter);
        f.setJpaProperties(additionalProperties());
        return f;
    }
    @Bean
    public PlatformTransactionManager answerTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(answerFactory().getObject());
        return transactionManager;
    }
    final Properties additionalProperties() {
        final Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto","update");
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        hibernateProperties.setProperty("hibernate.jdbc.time_zone", "Asia/Kolkata");
        hibernateProperties.setProperty("hibernate.show_sql","true");
        hibernateProperties.setProperty("hibernate.enable_lazy_load_no_trans","true");
        //hibernateProperties.setProperty("hibernate.show_sql","yes");
        return hibernateProperties;
    }
    @Bean(name="domain")
    public JdbcTemplate jdbcTemplate(){
        return  new JdbcTemplate(dataSource());
    }
}
