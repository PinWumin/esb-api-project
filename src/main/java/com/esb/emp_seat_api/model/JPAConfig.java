package com.esb.emp_seat_api.model;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@EnableJpaRepositories(basePackages = "com.esb.emp_seat_api.model", transactionManagerRef = "jpaTransactionManager")
@PropertySource("classpath:application.properties")
public class JPAConfig {

	private static final String[] ENTITYMANAGER_PACKAGES_TO_SCAN = { "com.esb.emp_seat_api.model.dao" };

	@Value("${spring.datasource.username}")
	private String username;
	@Value("${spring.datasource.password}")
	private String password;
	@Value("${spring.datasource.driverClassName}")
	private String driverClass;
	@Value("${spring.datasource.url}")
	private String url;

	@Bean
	public DataSource dataSource() {
		HikariDataSource hikariDataSource = new HikariDataSource();
		hikariDataSource.setJdbcUrl(url);
		hikariDataSource.setUsername(username);
		hikariDataSource.setPassword(password);
		hikariDataSource.setDriverClassName(driverClass);
		hikariDataSource.setConnectionTimeout(30000);
		hikariDataSource.setMaximumPoolSize(10);
		hikariDataSource.setMaxLifetime(1800000);
		hikariDataSource.setLeakDetectionThreshold(60000);
		return hikariDataSource;
	}

	@Bean
	public JpaTransactionManager jpaTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}

	private HibernateJpaVendorAdapter vendorAdaptor() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		return vendorAdapter;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setJpaVendorAdapter(vendorAdaptor());
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		entityManagerFactoryBean.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);

		return entityManagerFactoryBean;
	}
}
