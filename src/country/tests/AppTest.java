package country.tests;


import configuration.PersistentConfig;
import country.model.Country;
import country.service.IServiceWorker;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class AppTest {

    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(PersistentConfig.class);
    IServiceWorker serviceWorker = applicationContext.getBean(IServiceWorker.class);

    @Before
    public void setup() throws SQLException {
        org.h2.tools.Server.createTcpServer().start();
    }


    @Test
    public void shouldDealWithAddCountry() {

        /*Country expected=  new Country();
        expected.setCode("bl");
        expected.setName("Belgique");
        expected.setDevise("EURO");
        expected.setGreetings("Bonjour");
        Continent continent = new Continent();
        continent.setName("Europe");
        continent.setCode("eu");
        expected.setContinent(continent);*/
        serviceWorker.addCountry("bl,Belgique,EURO,Bonjour,eu");
        Country result = serviceWorker.getCountryByCode("bl");
        assertEquals("bl",result.getCode());
        assertEquals("Belgique",result.getName());
        assertEquals("EURO",result.getDevise());
        assertEquals("Bonjour",result.getGreetings());
        assertEquals("eu",result.getContinent().getCode());
        assertEquals("Europe",result.getContinent().getName());
    }

    @Test
    public void shouldDealWithGetInfos() {
        Country result = serviceWorker.getInfos("fr");
        assertEquals("fr",result.getCode());
        assertEquals("France",result.getName());
        assertEquals("EURO",result.getDevise());
        assertEquals("Bonjour",result.getGreetings());
        assertEquals("eu",result.getContinent().getCode());
        assertEquals("Europe",result.getContinent().getName());
    }

    @Test
    public void shouldDealWithDeleteCountry() {
        Country expected;
        serviceWorker.deleteCountry("fr");
        expected = serviceWorker.getCountryByCode("fr");
        assertThat(expected).isEqualTo(null);
    }

    @Test
    public void shouldDealWithUpdateCountry() {
         Country result;
         serviceWorker.updateCountry("fr", "fr,france,EURO,Bonjour!,af");
         result = serviceWorker.getCountryByCode("fr");
        assertEquals("fr",result.getCode());
        assertEquals("france",result.getName());
        assertEquals("EURO",result.getDevise());
        assertEquals("Bonjour!",result.getGreetings());
        assertEquals("af",result.getContinent().getCode());
        assertEquals("Africa",result.getContinent().getName());
    }
    @Test
    public void shouldDealWithGetCountries (){
        List<Country> List = serviceWorker.getCountries("eu");
        assertEquals(4, List.size());
    }


}


