package country.tests;


import country.model.Continent;
import country.model.Country;
import country.service.IServiceWorker;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class AppTest {

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans/*.xml");
    IServiceWorker serviceWorker = applicationContext.getBean(IServiceWorker.class);

    @Before
    public void setup() throws SQLException {
        org.h2.tools.Server.createTcpServer().start();
    }


    @Test
    public void shouldDealWithAddCountry() {

        Country expected=  new Country();
        expected.setCode("bl");
        expected.setName("Belgique");
        expected.setDevise("EURO");
        expected.setGreetings("Bonjour");
        Continent continent = new Continent();
        continent.setName("Europe");
        continent.setCode("eu");
        expected.setContinent(continent);
        serviceWorker.addCountry("bl,Belgique,EURO,Bonjour,eu");
        Country result = serviceWorker.getCountryByCode("bl");
        assertEquals(expected.getCode(),result.getCode());
        assertEquals(expected.getName(),result.getName());
        assertEquals(expected.getDevise(),result.getDevise());
        assertEquals(expected.getGreetings(),result.getGreetings());
        assertEquals(expected.getContinent().getCode(),result.getContinent().getCode());
        assertEquals(expected.getContinent().getName(),result.getContinent().getName());
    }
}


