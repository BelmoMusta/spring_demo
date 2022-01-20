package tests;

import config.Config;
import country.model.Country;
import country.service.IServiceWorker;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import java.sql.SQLException;

import static org.junit.Assert.assertEquals;


public class ClassTest {
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
    IServiceWorker serviceWorker = applicationContext.getBean(IServiceWorker.class);
    @Before
    public void setup() throws SQLException {
        org.h2.tools.Server.createTcpServer().start();
    }
    @Test
    public void addCountry() {
        serviceWorker.addCountry("mr,maroc,dh,salam,afr");
        Country result = serviceWorker.getCountry("mr");
        assertEquals("mr",result.getCode());
        assertEquals("maroc",result.getName());
        assertEquals("dh",result.getDevise());
        assertEquals("salam",result.getGreetings());
        assertEquals("afr",result.getContinent().getCode());
        assertEquals("Africa",result.getContinent().getName());
    }
}
