package com.dallas.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.dallas.vo.User;
import com.dallas.vo.Entity.WineEntity;

@Configuration // Mark this class as an configuration class.
@PropertySource("classpath:hibernate.properties") // it will look for "hibernate.properties" file from
													// "src/main/resources"
@EnableTransactionManagement
public class HibernateConfig {

	@Autowired
	private Environment env;
	

	// retreat database connection info from "hibernate.properties"
	@Bean
	public DataSource getDataSource() {
		System.out.println("getDataSources");
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getRequiredProperty("sql.driverClassName"));
		dataSource.setUrl(env.getRequiredProperty("sql.url"));
		dataSource.setUsername(env.getRequiredProperty("sql.username"));
		dataSource.setPassword(""); // I didn't set up a password for my database, therefore setPassword as null
		return dataSource;
	}

	// setup properties for hibernate
	private Properties hibernateProperties() {
		Properties properties = new Properties();
		System.out.println("hibernateProperties");
		//	each database has a different dialect, therefore we will have to set it up the dialect we would like to use
		properties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
		//	show sql in log
		properties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
		//	format sql
		properties.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
		//	automatically generate table.
		properties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
		
		System.out.println(env.getRequiredProperty("hibernate.dialect"));
		return properties;
	}

	// set up a session factory IOC for future use.
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(getDataSource()); // set up the data source information
		sessionFactory.setPackagesToScan(new String [] { // when it retreat data from database, hibernate knows where to
														// look for the Entities.
				"com.dallas.vo.Entity" });
		// This line is the one of the most important line because it tells hibernate which entity/database to use.
		sessionFactory.setAnnotatedClasses(User.class, WineEntity.class);
		sessionFactory.setHibernateProperties(hibernateProperties()); // set up the hibernate properties that has been
																		// defined.
		return sessionFactory;
	}

	// transactionmanager helps in
	@Bean
	public HibernateTransactionManager getTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}

}
