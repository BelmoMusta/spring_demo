package country.Configuration;

import country.model.Country;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import org.hibernate.SessionFactory;


import javax.sql.DataSource;
import java.util.Properties;
import country.model.Country;



@Configuration
@EnableTransactionManagement
//@ComponentScan(basePackages = "country")


public class Config {
	
	@Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[]  {"country.model" });
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }
	

    @Bean
    public DataSource dataSource(){
        /*BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:dataSource;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=false");
        dataSource.setUsername("sa");
        dataSource.setPassword("sa");
        return dataSource;*/
        
          return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2).addScript("classpath:database/db-schema.sql")
               .addScript("classpath:database/populate-database.sql").build();

    }

    
    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    private final Properties hibernateProperties() {
        Properties hibernatePropertie = new Properties();
        hibernatePropertie.setProperty("hibernate.hbm2ddl.auto", "create");
		//hibernatePropertie.setProperty("hibernate.connection.driver_class","org.h2.Driver");
        hibernatePropertie.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        hibernatePropertie.setProperty("hibernate.show_sql","true");
        //hibernatePropertie.setProperty("hibernate.format_sql","true");
        hibernatePropertie.setProperty("hibernate.hbm2ddl.import_files", "database/populate-database.sql");
        return hibernatePropertie;
    }
    
}








