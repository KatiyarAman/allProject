package com.quokka.map.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "mapFactory",transactionManagerRef = "mapTxn",basePackages = "com.quokka.map.repository")
public class JPAConfig {

    @Autowired
    ApplicationConfig config;

    @Primary
    @Bean(name = "mapDataSource")
    public  DataSource dataSource(){
        DriverManagerDataSource dataSource= new DriverManagerDataSource();
        dataSource.setDriverClassName(config.getDriver());
        dataSource.setUrl(config.getUrl1());
        dataSource.setUsername(config.getUser());
        dataSource.setPassword(config.getPassword());
        return dataSource;
    }

    @Primary
    @Bean(name = "mapFactory")
    public LocalContainerEntityManagerFactoryBean mapFactory(){
        final  LocalContainerEntityManagerFactoryBean factoryBean= new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setJpaProperties(additionalProperties());
        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        factoryBean.setJpaVendorAdapter(vendorAdapter);
        factoryBean.setPackagesToScan("com.quokka.map.entity");
        return  factoryBean;
    }
    @Primary
    @Bean(name = "mapTxn")
    public PlatformTransactionManager mapTxn(){
        final JpaTransactionManager jpaTransactionManager= new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(mapFactory().getObject());
        return jpaTransactionManager;
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

}
