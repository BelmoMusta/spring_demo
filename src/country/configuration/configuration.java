package country.configuration;
import java.util.Properties;


import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages =  "country")

public class configuration {
	
	    @Bean
	    public DataSource dataSource() {
	        BasicDataSource ds = new BasicDataSource();
	        ds.setDriverClassName("org.h2.Driver");
	        ds.setUrl("jdbc:h2:mem:db");
	        ds.setUsername("sa");
	        ds.setPassword("sa");
	        
	        return ds;
	    }
	    private final Properties hibernateProperties() {
	        Properties h_Properties = new Properties();
	        h_Properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
	        h_Properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");

	        return h_Properties;
	    }
	    @Bean
	    public LocalSessionFactoryBean sessionFactory() {
	        LocalSessionFactoryBean sf = new LocalSessionFactoryBean();
	        sf.setDataSource(dataSource());
	        sf.setPackagesToScan("country" );
	        sf.setHibernateProperties(hibernateProperties());

	        return sf;
	    }

	    @Bean
	    public PlatformTransactionManager hibernateTransactionManager() {
	        HibernateTransactionManager tm = new HibernateTransactionManager();
	        tm.setSessionFactory(sessionFactory().getObject());
	        return tm;
	    }

}
