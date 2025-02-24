package com.database.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "user-twoEntityManagerFactory",
        transactionManagerRef = "user-twoTransactionManager",
        basePackages = { "com.database.entities", "com.database.repo2" })
public class DatabaseTwoConfiguration {

    @Bean(name="user-twoProperties")
    @ConfigurationProperties("spring.datasource.user-two")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name="user-twoDatasource")
    @ConfigurationProperties(prefix = "spring.datasource.user-two")
    public DataSource datasource(@Qualifier("user-twoProperties") DataSourceProperties properties){
        return properties.initializeDataSourceBuilder().build();
    }
    
    @Bean(name="user-twoEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean
            (EntityManagerFactoryBuilder builder,  @Qualifier("user-twoDatasource") DataSource dataSource){
    	LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
    	bean.setDataSource(dataSource);
    	Properties properties = new Properties();
    	properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
    	properties.setProperty("spring.jpa.hibernate.ddl-auto", "create");
    	
    	bean.setPackagesToScan("com.database.entities");
    	HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
    	bean.setJpaVendorAdapter(adapter);
    	bean.setJpaProperties(properties);
    return bean;
    }
    
    @Bean(name = "user-twoTransactionManager")
    @ConfigurationProperties("spring.jpa")
    public PlatformTransactionManager transactionManager(
            @Qualifier("user-twoEntityManagerFactory") EntityManagerFactory entityManagerFactory) {

        return new JpaTransactionManager(entityManagerFactory);
    }
}