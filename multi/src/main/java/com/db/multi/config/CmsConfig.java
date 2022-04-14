package com.db.multi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(entityManagerFactoryRef = "cmsFactory",transactionManagerRef = "cmsTxn",basePackages = "com.db.multi.cms.repository")
public class CmsConfig {
    @Autowired
    ApplicationConfig config;

    @Primary
    @Bean(name = "componetDatasource")
    public DataSource componentDataSource() {
        final DriverManagerDataSource dataSource= new DriverManagerDataSource();
        dataSource.setDriverClassName(config.getDriverName());
        dataSource.setUrl(config.getUrl());
        dataSource.setUsername(config.getUserName());
        dataSource.setPassword(config.getPassword());
        return dataSource;
    }

//    @Bean
//    public LocalContainerEntityManagerFactoryBean cmsFactory(){
//        final LocalContainerEntityManagerFactoryBean cmsFactory= new LocalContainerEntityManagerFactoryBean();
//        cmsFactory.setDataSource(componentDataSource());
//        cmsFactory.setPackagesToScan("com.db.multi.cms.entity");
//        cmsFactory.setJpaProperties(additionalProperties());
//        final HibernateJpaVendorAdapter jpaVendorAdapter= new HibernateJpaVendorAdapter();
//        cmsFactory.setJpaVendorAdapter(jpaVendorAdapter);
//return cmsFactory;
//    }
    final Properties additionalProperties() {
        final Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto","update");
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        hibernateProperties.setProperty("hibernate.jdbc.time_zone", "Asia/Kolkata");
        hibernateProperties.setProperty("hibernate.show_sql","true");
        hibernateProperties.setProperty("hibernate.format_sql","true");

        //hibernateProperties.setProperty("hibernate.show_sql","yes");
        return hibernateProperties;
    }
    @Primary
    @Bean(name = "transactionManager")
    @Autowired
    public DataSourceTransactionManager transactionManager(@Qualifier("componetDatasource") DataSource componetDatasource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(componetDatasource);
        return transactionManager;
    }
}
