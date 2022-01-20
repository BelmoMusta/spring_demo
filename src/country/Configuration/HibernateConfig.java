package country.Configuration;


import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "country.Configuration" })

public class HibernateConfig
{
	
	@Bean
	public LocalSessionFactoryBean sessionFactory()
	{
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan("country.model" );
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	@Bean
	public DataSource dataSource()
	{
	return new EmbeddedDatabaseBuilder().generateUniqueName(true)
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:database/db-schema.sql")
                .addScript("classpath:database/populate-database.sql").build();
	}
   
	private Properties hibernateProperties()
	{
		Properties properties = new Properties();
		properties.put("hibernate.connection.url","jdbc:h2:./db-schema");
		properties.put("hibernate.connection.username","sa");
		properties.put("hibernate.connection.password","");
		properties.put("hibernate.current_session_context_class", "thread");
		properties.put("hibernate.connection.driver_class","org.h2.Driver");
		properties.put("hibernate.hbm2ddl.auto","update");
		properties.put("hibernate.dialect","org.hibernate.dialect.H2Dialect");
		properties.put("hibernate.show_sql", "false");
		properties.put("hibernate.format_sql","false");
		return properties;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory session)
	{
		HibernateTransactionManager tranManager = new HibernateTransactionManager();
		tranManager.setSessionFactory(session);
		return tranManager;
	}
}


