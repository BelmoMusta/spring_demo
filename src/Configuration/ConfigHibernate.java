package Configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import country.model.Country;

@Configuration
@EnableTransactionManagement
public class ConfigHibernate {
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan("country");
		sessionFactory.setAnnotatedClasses(Country.class);
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}
    
	private final Properties hibernateProperties() {
		
		 Properties hibernateProperties = new Properties();
	        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "create");
	        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
	        hibernateProperties.setProperty("hibernate.show_sql","true");
	        hibernateProperties.setProperty("hibernate.hbm2ddl.import_files", "database/populate-database.sql");
	        return hibernateProperties;
		
	}
	
	@Bean
	private DataSource dataSource() {
		String script1="classpath:database/db-schema.sql";
		String script2="classpath:database/populate-database.sql";
		return (DataSource) new EmbeddedDatabaseBuilder().generateUniqueName(true)
				   .setType(EmbeddedDatabaseType.H2)
				   .addScript(script1)
				   .addScript(script2);
            
	}
	@Bean
	public PlatformTransactionManager hibernateTransactionManager() {
	        HibernateTransactionManager transactionManager
	          = new HibernateTransactionManager();
	        transactionManager.setSessionFactory(sessionFactory().getObject());
	        return transactionManager;
	 }


}  
