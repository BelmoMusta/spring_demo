package testing;

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

public class testingApp {

    ApplicationContext applicationContext =
            new ClassPathXmlApplicationContext("beans/*.xml");
    IServiceWorker serviceWorker = applicationContext.getBean(IServiceWorker.class);

    @Test
    public void testOne() {

        Country expectedCountry=  new Country("maroc","ma","MAD","Salam",new Continent("Africa","af"));

        String newCountry = "ma,maroc,MAD,Salam,af";
        serviceWorker.addCountry(newCountry);
        Country actualCountry = serviceWorker.getInformations("ma");

        assertEquals(expectedCountry.getCode(),actualCountry.getCode());
        assertEquals(expectedCountry.getName(),actualCountry.getName());
        assertEquals(expectedCountry.getDevise(),actualCountry.getDevise());
        assertEquals(expectedCountry.getGreetings(),actualCountry.getGreetings());
        assertEquals(expectedCountry.getContinent().getCode(),actualCountry.getContinent().getCode());
        assertEquals(expectedCountry.getContinent().getName(),actualCountry.getContinent().getName());

    }
    @Test
    public void testTwo() {

        Country expectedCountry=  new Country("France","fr","EURO","Bonjour",new Continent("Europe","eu"));

        Country actualCountry = serviceWorker.getInformations("fr");

        assertEquals(expectedCountry.getCode(),actualCountry.getCode());
        assertEquals(expectedCountry.getName(),actualCountry.getName());
        assertEquals(expectedCountry.getDevise(),actualCountry.getDevise());
        assertEquals(expectedCountry.getGreetings(),actualCountry.getGreetings());
        assertEquals(expectedCountry.getContinent().getCode(),actualCountry.getContinent().getCode());
        assertEquals(expectedCountry.getContinent().getName(),actualCountry.getContinent().getName());

    }

    @Test
    public void testThree() {

        Country france = serviceWorker.getInformations("fr");
        assertTrue(serviceWorker.getCountries("eu").stream().map(Country::getCode).collect(Collectors.toList()).contains(france.getCode()));
        serviceWorker.deleteCountry("fr");
        assertTrue(!serviceWorker.getCountries("eu").stream().map(Country::getCode).collect(Collectors.toList()).contains(france.getCode()));

    }

    @Test
    public void testFour() {

        Country expectedCountry=  new Country("maroc","ma","MAD","Salam",new Continent("Africa","af"));

        String newCountry = "ma,maroc,MAD,Salam,af";
        serviceWorker.updateCountry("fr",newCountry);
        Country actualCountry = serviceWorker.getInformations("ma");

        assertEquals(expectedCountry.getCode(),actualCountry.getCode());
        assertEquals(expectedCountry.getName(),actualCountry.getName());
        assertEquals(expectedCountry.getDevise(),actualCountry.getDevise());
        assertEquals(expectedCountry.getGreetings(),actualCountry.getGreetings());
        assertEquals(expectedCountry.getContinent().getCode(),actualCountry.getContinent().getCode());
        assertEquals(expectedCountry.getContinent().getName(),actualCountry.getContinent().getName());

    }

    @Test
    public void testFive() {

        Country france = serviceWorker.getInformations("fr");
        Country germany = serviceWorker.getInformations("es");
        Country england = serviceWorker.getInformations("en");
        Country spain = serviceWorker.getInformations("de");

        List<String> europeCountryList =  serviceWorker.getCountries("eu").stream().map(Country::getCode).collect(Collectors.toList());

        assertTrue(europeCountryList.contains(france.getCode()));
        assertTrue(europeCountryList.contains(germany.getCode()));
        assertTrue(europeCountryList.contains(spain.getCode()));
        assertTrue(europeCountryList.contains(england.getCode()));
        assertTrue(europeCountryList.size()==4);

    }

}
