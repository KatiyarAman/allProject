package com.quokaa.excel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "cmsFactory",transactionManagerRef = "cmsTxn",basePackages = "com.quokaa.excel.cms.repository")
@Configuration
public class CmsConfig {

    @Autowired
    ApplicationConfig config;

    @Bean(name = "cmsDataSource")
    public DataSource cmsdataSource(){
        final DriverManagerDataSource dataSource= new DriverManagerDataSource();
        dataSource.setDriverClassName(config.getDriver());
        dataSource.setUrl(config.getCms_url());
        dataSource.setUsername(config.getUser());
        dataSource.setPassword(config.getPassword());
        return dataSource;
    }

    @Bean(name = "cmsFactory")
    public LocalContainerEntityManagerFactoryBean cmsFactory(){
        final LocalContainerEntityManagerFactoryBean factoryBean= new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource( cmsdataSource());
        HibernateJpaVendorAdapter jpaVendorAdapter= new HibernateJpaVendorAdapter();
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        factoryBean.setJpaProperties(additionalProperties());
        factoryBean.setPackagesToScan("com.quokaa.excel.cms");
        return factoryBean;
    }

    @Bean(name = "cmsTxn")
    public PlatformTransactionManager cmsTxn(){
       final JpaTransactionManager jpa= new JpaTransactionManager();
        jpa.setEntityManagerFactory( cmsFactory().getObject());
        return jpa;
    }
    final Properties additionalProperties() {
        final Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto","update");
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        hibernateProperties.setProperty("hibernate.jdbc.time_zone", "Asia/Kolkata");
        //hibernateProperties.setProperty("hibernate.show_sql","true");
        return hibernateProperties;
    }
}
