package test;

import appConfig.PersistentAppConfig;
import country.dao.CountryDAO;
import country.model.Continent;
import country.model.Country;

import country.service.IServiceWorker;
import country.service.impl.CountryServiceImpl;
import org.junit.Test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;




public class AppTest {


    ApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(PersistentAppConfig.class);
    CountryDAO countryDAO = applicationContext.getBean(CountryDAO.class);

    IServiceWorker serviceWorker = applicationContext.getBean(IServiceWorker.class);

    @Test
    public void testAspectFunctionalOne() {

        Continent continent = new Continent("Europe","eur");
        Country  country=  new Country("france","FR","EUR","SALAM",continent);


        countryDAO.addNewCountry(country);

        Country actualCountry = countryDAO.getByCode("FR");
         System.out.println(actualCountry.getCode());

        assertEquals(country.getCode(),actualCountry.getCode());

        assertEquals(country.getName(),actualCountry.getName());
        assertEquals(country.getDevise(),actualCountry.getDevise());
        assertEquals(country.getGreetings(),actualCountry.getGreetings());
        assertEquals(country.getContinent().getCode(),actualCountry.getContinent().getCode());
        assertEquals(country.getContinent().getName(),actualCountry.getContinent().getName());

    }

    @Test
    public void testAspectFunctionalTwo() {

        Continent continent = new Continent("Europe","eur");
        Country  country=  new Country("Spain","es","EURO","Hola",continent);

        Country actualCountry = countryDAO.getByCode("es");


        assertEquals(country.getCode(),actualCountry.getCode());
        assertEquals(country.getName(),actualCountry.getName());
        assertEquals(country.getDevise(),actualCountry.getDevise());
        assertEquals(country.getGreetings(),actualCountry.getGreetings());
        assertEquals(country.getContinent().getCode(),actualCountry.getContinent().getCode());
        assertEquals(country.getContinent().getName(),actualCountry.getContinent().getName());

    }
    @Test
    public void testAspectFunctionalThree() {

        Country country= countryDAO.getByCode("en");
        countryDAO.deleteCountry(country);

         Country expected = (Country) countryDAO.getByCode("en");

        assertEquals(expected,null);
    }


    @Test
    public void testAspectFunctionalFour() {




        Country country = countryDAO.getByCode("fr");

        country.setCode("FR");
        country.setName("FRANCE");
        country.setDevise("EUR");
        country.setGreetings("BONJOUUUUR");
        country.setContinent(new Continent("Europe","eur"));


        countryDAO.updateCountry(country);


        Country actualCountry = countryDAO.getByCode("FR");

        assertEquals(country.getCode(),actualCountry.getCode());
        assertEquals(country.getName(),actualCountry.getName());
        assertEquals(country.getDevise(),actualCountry.getDevise());
        assertEquals(country.getGreetings(),actualCountry.getGreetings());
        assertEquals(country.getContinent().getCode(),actualCountry.getContinent().getCode());
        assertEquals(country.getContinent().getName(),actualCountry.getContinent().getName());

    }


    @Test
    public void testAspectFunctionalFive() {

        List<Country> List = countryDAO.getCountries("eur");
        assertEquals(4, List.size());





    }


}
