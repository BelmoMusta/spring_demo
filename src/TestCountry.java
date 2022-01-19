import country.dao.CountryDAO;
import country.model.Country;
import country.service.IServiceWorker;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestCountry {

    ApplicationContext applicationContext =
            new ClassPathXmlApplicationContext("beans/*.xml");
    IServiceWorker serviceWorker = applicationContext.getBean(IServiceWorker.class);

    CountryDAO countryDAO = applicationContext.getBean(CountryDAO.class);
    @Before
    public void setup() throws SQLException {
        org.h2.tools.Server.createTcpServer().start();
    }

    @Test
    public void AddCountry(){
    String newCountry= "Egypt,EGP,Junayh,SALAM,AF";
    serviceWorker.SaveCountry(newCountry);
    Country country = serviceWorker.GetCountryByCode("EGP");

    assertEquals("EGP",country.getCode());
    assertEquals("Egypt",country.getName());
    assertEquals("Junayh",country.getDevise());
    assertEquals("SALAM",country.getGreetings());
    assertEquals("AF",country.getContinent().getCode());

    }

    @Test
    public void GetCountry(){

        Country country = serviceWorker.GetCountryByCode("fr");
        assertEquals("fr",country.getCode());
        assertEquals("France",country.getName());
        assertEquals("EURO",country.getDevise());
        assertEquals("Bonjour",country.getGreetings());
        assertEquals("EU",country.getContinent().getCode());

    }

    @Test
    public void DeleteCountry(){

        serviceWorker.DeleteCountry("fr");
        Country country=serviceWorker.GetCountryByCode("fr");
        assertEquals(null,country);
    }

    @Test
    public void UpdateCountry(){
        String countryUpdate= "France,Franc,Bonsoir,EU";
        serviceWorker.UpdateCountry("fr",countryUpdate);
        Country country = serviceWorker.GetCountryByCode("fr");
        assertEquals("fr",country.getCode());
        assertEquals("France",country.getName());
        assertEquals("Franc",country.getDevise());
        assertEquals("Bonsoir",country.getGreetings());
        assertEquals("EU",country.getContinent().getCode());
    }

    @Test
    public void GetCountriesByontinentCode(){
        Country france = serviceWorker.GetCountryByCode("fr");
        Country german = serviceWorker.GetCountryByCode("de");
        Country spain = serviceWorker.GetCountryByCode("es");
        Country england = serviceWorker.GetCountryByCode("en");

        List<Country> listC = countryDAO.CountriesByContinentCode("EU");
        listC.toString();
        assertEquals(france.getCode(),listC.get(0).getCode());
        assertEquals(spain.getCode(),listC.get(1).getCode());
        assertEquals(england.getCode(),listC.get(2).getCode());
        assertEquals(german.getCode(),listC.get(3).getCode());


    }


}
