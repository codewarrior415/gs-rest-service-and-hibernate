package jpa.config.java;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.ejb.HibernatePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class JpaConfig {

	@Bean
	public EntityManagerFactory entityManagerFactory() {

		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(datasource());
		entityManagerFactory.setPackagesToScan(new String[] { "jpa.config.java", "corp.entities" });
		entityManagerFactory.setPersistenceProvider(new HibernatePersistence());
		entityManagerFactory.afterPropertiesSet();
		return entityManagerFactory.getObject();
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager(entityManagerFactory());
		transactionManager.setDataSource(datasource());
		transactionManager.setJpaDialect(new HibernateJpaDialect());
		return transactionManager;
	}

	@Bean
	public DataSource datasource() {
		DriverManagerDataSource datasource = new DriverManagerDataSource();
		
		datasource.setDriverClassName("com.mysql.jdbc.Driver");
		datasource.setUrl("jdbc:mysql://localhost/test");
		datasource.setUsername("root");
		datasource.setPassword("");
		
		return datasource;
	}
	
	@Bean
	public DataSourceInitializer dataSourceInitializer() {
		DataSourceInitializer dsi = new DataSourceInitializer();
		
		dsi.setDataSource(datasource());
		
		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
		databasePopulator.addScript(new ClassPathResource("jpa/config/java/schema.sql"));

		dsi.setDatabasePopulator(databasePopulator);
		
		return dsi;
	}
        
        
        
}
