package test;

import country.model.Continent;
import country.model.Country;
import country.service.IServiceWorker;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestApp {

    ApplicationContext applicationContext =
            new ClassPathXmlApplicationContext("beans/*.xml");
    IServiceWorker serviceWorker = applicationContext.getBean(IServiceWorker.class);

    @Test
    public void premierTest() {

        Country expectedCountry=  new Country("MA","Maroc","MAD","Samoualikom",new Continent("AF","Africa"));

        String newCountry = "MA,Maroc,MAD,Samoualikom,AF";
        serviceWorker.addNewCountry(newCountry);
        Country actualCountry = serviceWorker.getCountryInformations("MA");

        assertEquals(expectedCountry.getCode(),actualCountry.getCode());
        assertEquals(expectedCountry.getName(),actualCountry.getName());
        assertEquals(expectedCountry.getDevise(),actualCountry.getDevise());
        assertEquals(expectedCountry.getGreetings(),actualCountry.getGreetings());
        assertEquals(expectedCountry.getContinent().getCode(),actualCountry.getContinent().getCode());
        assertEquals(expectedCountry.getContinent().getName(),actualCountry.getContinent().getName());

    }
    @Test
    public void deuxiemeTest() {

        Country expectedCountry=  new Country("FR","France","EURO","Bonjour",new Continent("EU","Europe"));

        Country actualCountry = serviceWorker.getCountryInformations("FR");

        assertEquals(expectedCountry.getCode(),actualCountry.getCode());
        assertEquals(expectedCountry.getName(),actualCountry.getName());
        assertEquals(expectedCountry.getDevise(),actualCountry.getDevise());
        assertEquals(expectedCountry.getGreetings(),actualCountry.getGreetings());
        assertEquals(expectedCountry.getContinent().getCode(),actualCountry.getContinent().getCode());
        assertEquals(expectedCountry.getContinent().getName(),actualCountry.getContinent().getName());

    }

    @Test
    public void troisiemeTest() {

        Country france = serviceWorker.getCountryInformations("FR");
        assertTrue(serviceWorker.getCountriesByContinent("EU").stream().map(Country::getCode).collect(Collectors.toList()).contains(france.getCode()));
        serviceWorker.deleteCountry("FR");
        assertTrue(!serviceWorker.getCountriesByContinent("EU").stream().map(Country::getCode).collect(Collectors.toList()).contains(france.getCode()));

    }

    @Test
    public void quatriemeTest() {

        Country expectedCountry=  new Country("MA","Maroc","MAD","Samoualikom",new Continent("AF","Africa"));

        String newCountry = "MA,Maroc,MAD,Samoualikom,AF";
        serviceWorker.updateCountry("FR",newCountry);
        Country actualCountry = serviceWorker.getCountryInformations("MA");

        assertEquals(expectedCountry.getCode(),actualCountry.getCode());
        assertEquals(expectedCountry.getName(),actualCountry.getName());
        assertEquals(expectedCountry.getDevise(),actualCountry.getDevise());
        assertEquals(expectedCountry.getGreetings(),actualCountry.getGreetings());
        assertEquals(expectedCountry.getContinent().getCode(),actualCountry.getContinent().getCode());
        assertEquals(expectedCountry.getContinent().getName(),actualCountry.getContinent().getName());

    }

    @Test
    public void cinquiemeTest() {

        Country france = serviceWorker.getCountryInformations("FR");
        Country germany = serviceWorker.getCountryInformations("ES");
        Country england = serviceWorker.getCountryInformations("EN");
        Country spain = serviceWorker.getCountryInformations("DE");

        List<String> europeCountryList =  serviceWorker.getCountriesByContinent("EU").stream().map(Country::getCode).collect(Collectors.toList());

        assertTrue(europeCountryList.contains(france.getCode()));
        assertTrue(europeCountryList.contains(germany.getCode()));
        assertTrue(europeCountryList.contains(spain.getCode()));
        assertTrue(europeCountryList.contains(england.getCode()));
        assertTrue(europeCountryList.size()==4);

    }

}
