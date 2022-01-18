package beans;

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
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "beans" })
@PropertySource(value = { "classpath:app.properties" })
public class Hibernate
{
	@Autowired
	private Environment env;
	@Bean
	public LocalSessionFactoryBean sessionFactory()
	{
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[] { "country.model" });
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	@Bean
	public DataSource dataSource()
	{
		DriverManagerDataSource DS = new DriverManagerDataSource();
		DS.setDriverClassName(env.getRequiredProperty("jdbc.driverClassName"));
		DS.setUrl(env.getRequiredProperty("jdbc.url"));
		DS.setUsername(env.getRequiredProperty("jdbc.username"));
		DS.setPassword(env.getRequiredProperty("jdbc.password"));
		return DS;
	}

	private Properties hibernateProperties()
	{
		Properties props = new Properties();
		props.put("hibernate.dialect",env.getRequiredProperty("hibernate.dialect"));
		props.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
		props.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
		return props;
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

