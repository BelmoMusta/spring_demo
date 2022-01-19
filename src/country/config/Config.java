package country.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import country.model.Continent;
import country.model.Country;

@ComponentScan(basePackages = "country")
@Configuration
public class Config {
	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:mem:dataSource;DB_CLOSE_DELAY=-1");
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		//add schema script
//		Resource initSchema = new ClassPathResource("database/db-schema.sql");
//		DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initSchema);
//		DatabasePopulatorUtils.execute(databasePopulator, dataSource);
//		//add initial data 
//		initSchema  = new ClassPathResource("database/populate-database.sql");
//		databasePopulator = new ResourceDatabasePopulator(initSchema);
//		DatabasePopulatorUtils.execute(databasePopulator, dataSource);
		return dataSource;
	}
	@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(getDataSource());
		sessionFactory.setAnnotatedClasses(Country.class,Continent.class);
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "create");
//		properties.setProperty("javax.persistence.sql-load-script-source", "database/populate-database.sql");
		sessionFactory.setHibernateProperties(properties);
		return sessionFactory;
	}

}
