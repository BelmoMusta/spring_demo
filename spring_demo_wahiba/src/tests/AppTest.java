package tests;

import country.model.Continent;
import country.model.Country;
import country.service.IServiceWorker;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AppTest {
    String MA = "ma";
    String AFRICA = "Africa";
    String MAROC = "Maroc";
    String DIRHAM = "dirham";
    String SALAM = "Salam";


    String EUROPE = "Europe";
    String FRANCE = "France";
    String EURO = "EURO";
    String BONJOUR = "Bonjour";

    String EN = "en";
    String ES = "es";
    String DE = "de";
    String FR = "fr";

    String AFR = "afr";
    String EUR = "eur";
    String ASIA = "asia";
    String AUS = "aus";
    String AME = "ame";

    String VIRGULE = ",";
    String MODIFIED = "Modified!";

    ApplicationContext applicationContext =
            new ClassPathXmlApplicationContext("beans/*.xml");
    IServiceWorker serviceWorker = applicationContext.getBean(IServiceWorker.class);

    @Before
    public void setup() throws SQLException {
        org.h2.tools.Server.createTcpServer().start();
    }


    @Test
    public void firstAspectTest() {

        Country expectedCountry=  new Country();
        expectedCountry.setContinent(new Continent());

        expectedCountry.setCode(MA);
        expectedCountry.setName(MAROC);
        expectedCountry.setDevise(DIRHAM);
        expectedCountry.setGreetings(SALAM);
        expectedCountry.getContinent().setCode(AFR);
        expectedCountry.getContinent().setName(AFRICA);
        String newCountry = MA + VIRGULE + MAROC + VIRGULE + DIRHAM + VIRGULE + SALAM + VIRGULE + AFR;
        serviceWorker.addCountry(newCountry);
        Country actualCountry = serviceWorker.getCountryData(MA);

        assertEquals(expectedCountry.getCode(),actualCountry.getCode());
        assertEquals(expectedCountry.getName(),actualCountry.getName());
        assertEquals(expectedCountry.getDevise(),actualCountry.getDevise());
        assertEquals(expectedCountry.getGreetings(),actualCountry.getGreetings());
        assertEquals(expectedCountry.getContinent().getCode(),actualCountry.getContinent().getCode());
        assertEquals(expectedCountry.getContinent().getName(),actualCountry.getContinent().getName());


    }
    @Test
    public void secondAspectTest() throws SQLException {

        Country expectedCountry=  new Country();
        expectedCountry.setContinent(new Continent());

        expectedCountry.setCode(FR);
        expectedCountry.setName(FRANCE);
        expectedCountry.setDevise(EURO);
        expectedCountry.setGreetings(BONJOUR);
        expectedCountry.getContinent().setCode(EUR);
        expectedCountry.getContinent().setName(EUROPE);

        Country actualCountry = serviceWorker.getCountryData(FR);

        assertEquals(expectedCountry.getCode(),actualCountry.getCode());
        assertEquals(expectedCountry.getName(),actualCountry.getName());
        assertEquals(expectedCountry.getDevise(),actualCountry.getDevise());
        assertEquals(expectedCountry.getGreetings(),actualCountry.getGreetings());
        assertEquals(expectedCountry.getContinent().getCode(),actualCountry.getContinent().getCode());
        assertEquals(expectedCountry.getContinent().getName(),actualCountry.getContinent().getName());

    }

    @Test
    public void thirdAspectTest() {

        int sizeBeforeDelete = serviceWorker.getCountriesByContinent(EUR).size()+
                        serviceWorker.getCountriesByContinent(AFR).size()+
                        serviceWorker.getCountriesByContinent(AME).size()+
                        serviceWorker.getCountriesByContinent(ASIA).size()+
                        serviceWorker.getCountriesByContinent(AUS).size();

        serviceWorker.deleteCountry(FR);

        int sizeAfterDelete = serviceWorker.getCountriesByContinent(EUR).size()+
                serviceWorker.getCountriesByContinent(AFR).size()+
                serviceWorker.getCountriesByContinent(AME).size()+
                serviceWorker.getCountriesByContinent(ASIA).size()+
                serviceWorker.getCountriesByContinent(AUS).size();

        assertEquals(sizeAfterDelete, (sizeBeforeDelete-1));

    }

    @Test
    public void fourthAspectTest() {

        Country expectedCountry=  new Country();
        expectedCountry.setContinent(new Continent());

        expectedCountry.setCode(FR);
        expectedCountry.setName(MODIFIED);
        expectedCountry.setDevise(MODIFIED);
        expectedCountry.setGreetings(MODIFIED);
        expectedCountry.getContinent().setCode(AFR);
        expectedCountry.getContinent().setName(AFRICA);

        String newCountry = FR + VIRGULE + MODIFIED + VIRGULE + MODIFIED + VIRGULE + MODIFIED + VIRGULE + AFR;
        serviceWorker.updateCountry(FR,newCountry);
        Country actualCountry = serviceWorker.getCountryData(FR);

        assertEquals(expectedCountry.getCode(),actualCountry.getCode());
        assertEquals(expectedCountry.getName(),actualCountry.getName());
        assertEquals(expectedCountry.getDevise(),actualCountry.getDevise());
        assertEquals(expectedCountry.getGreetings(),actualCountry.getGreetings());
        assertEquals(expectedCountry.getContinent().getCode(),actualCountry.getContinent().getCode());
        assertEquals(expectedCountry.getContinent().getName(),actualCountry.getContinent().getName());


    }

    @Test
    public void fifthAspectTest() {
        Country france = serviceWorker.getCountryData(FR);
        Country germany = serviceWorker.getCountryData(DE);
        Country spain = serviceWorker.getCountryData(ES);
        Country england = serviceWorker.getCountryData(EN);


        List<String> europeCountryList =  serviceWorker.getCountriesByContinent(EUR).stream().map(Country::getCode).collect(Collectors.toList());

        assertTrue(europeCountryList.contains(france.getCode()));
        assertTrue(europeCountryList.contains(germany.getCode()));
        assertTrue(europeCountryList.contains(spain.getCode()));
        assertTrue(europeCountryList.contains(england.getCode()));


    }

}
