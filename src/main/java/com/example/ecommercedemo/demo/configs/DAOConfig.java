/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ecommercedemo.demo.configs;

import com.example.ecommercedemo.demo.models.BookProductPersistenceEntityModel;
import com.example.ecommercedemo.demo.models.ComputerProductPersistenceEntityModel;
import com.example.ecommercedemo.demo.models.MobileProductPersistenceEntityModel;
import com.example.ecommercedemo.demo.models.OrderItemPersistenceEntityModel;
import com.example.ecommercedemo.demo.models.OrderPersistenceEntityModel;
import com.example.ecommercedemo.demo.models.ProductPersistenceEntityModel;
import com.example.ecommercedemo.demo.models.ProductTypesPersistenceEntityModel;
import com.example.ecommercedemo.demo.models.UserCartPersistenceEntityModel;
import com.example.ecommercedemo.demo.models.UserPersistenceEntityModel;
import java.util.Properties;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

/**
 *
 * @author The_Humble_Fool
 */
@Configuration
public class DAOConfig {

    @Value(value = "${spring.datasource.driver-class-name}")
    private String driverClass;
    @Value(value = "${spring.datasource.url}")
    private String url;
    @Value(value = "${spring.datasource.username}")
    private String username;
    @Value(value = "${spring.datasource.password}")
    private String password;
    @Value(value = "${spring.jpa.properties.hibernate.dialect}")
    private String dialect;
    @Value(value = "${spring.jpa.hibernate.ddl-auto}")
    private String ddl_auto;
//    @Value(value = "${spring.jpa.show-sql}")
//    private String show_sql;
//    @Value(value = "${spring.jpa.format-sql}")
//    private String format_sql;

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(driverClass);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }

    @Bean
    public LocalSessionFactoryBean entityManagerFactory() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty(Environment.DIALECT, dialect);
        hibernateProperties.setProperty(Environment.HBM2DDL_AUTO, ddl_auto);
//        hibernateProperties.setProperty(Environment.SHOW_SQL, show_sql);
//        hibernateProperties.setProperty(Environment.FORMAT_SQL, format_sql);
        sessionFactoryBean.setHibernateProperties(hibernateProperties);
        sessionFactoryBean.setAnnotatedClasses(UserPersistenceEntityModel.class, ProductPersistenceEntityModel.class, BookProductPersistenceEntityModel.class, ComputerProductPersistenceEntityModel.class, MobileProductPersistenceEntityModel.class, ProductTypesPersistenceEntityModel.class, UserCartPersistenceEntityModel.class, OrderPersistenceEntityModel.class, OrderItemPersistenceEntityModel.class);
        return sessionFactoryBean;

    }
}
