package appConfig;

import country.model.Continent;
import country.model.Country;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "country")
@EnableTransactionManagement

public class PersistentAppConfig {

    @Bean
    public DataSource dataSource(){

        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:dataSource;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=false");
        dataSource.setUsername("sa");

        return dataSource;

    }

    @Bean
    public LocalSessionFactoryBean localSessionFactory() {

        LocalSessionFactoryBean localSessionFactory = new LocalSessionFactoryBean();
        localSessionFactory.setDataSource(dataSource());
        localSessionFactory.setPackagesToScan(new String[]  {"country.model" });
        localSessionFactory.setHibernateProperties(hibernateProperties());
        localSessionFactory.setAnnotatedClasses(Country.class);

        return localSessionFactory;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(localSessionFactory().getObject());
        return transactionManager;
    }

    private Properties hibernateProperties() {

        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "create");
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        hibernateProperties.setProperty("hibernate.show_sql","true");
        hibernateProperties.setProperty("hibernate.hbm2ddl.import_files","database/populate-database.sql");
        return hibernateProperties;
    }





}
