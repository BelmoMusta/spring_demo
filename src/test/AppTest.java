package test;

import country.model.*;
import country.service.IServiceWorker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.*;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;


    public class AppTest{
        
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("beans/*.xml");
        IServiceWorker serviceWorker = applicationContext.getBean(IServiceWorker.class);


        @Test
        public void testAjout() {

            Country countryvalues=  new Country("Guinée","gn","GNF","Bonjour !",new Continent("Africa","af"));

            String countrytoadd= "gn,Guinée,GNF,Bonjour !,af";
            serviceWorker.addCountry(countrytoadd);

            Country countryverification = serviceWorker.getCountryInfos("gn");

            assertEquals(countryvalues.getCode(),countryverification.getCode());
            assertEquals(countryvalues.getName(),countryverification.getName());
            assertEquals(countryvalues.getDevise(),countryverification.getDevise());
            assertEquals(countryvalues.getGreetings(),countryverification.getGreetings());
            assertEquals(countryvalues.getContinent().getCode(),countryverification.getContinent().getCode());
            assertEquals(countryvalues.getContinent().getName(),countryverification.getContinent().getName());

        }
        @Test
        public void testInfosPays() {

            Country countryvalues=  new Country("England","en","GBP","Hello",new Continent("Europe","eu"));

            Country countryverification = serviceWorker.getCountryInfos("en");

            assertEquals(countryvalues.getCode(),countryverification.getCode());
            assertEquals(countryvalues.getName(),countryverification.getName());
            assertEquals(countryvalues.getDevise(),countryverification.getDevise());
            assertEquals(countryvalues.getGreetings(),countryverification.getGreetings());
            assertEquals(countryvalues.getContinent().getCode(),countryverification.getContinent().getCode());
            assertEquals(countryvalues.getContinent().getName(),countryverification.getContinent().getName());

        }

        @Test
        public void testSuppression() {

            Country spain = serviceWorker.getCountryInfos("es");
            assertTrue(serviceWorker.getCountries("eu").stream().map(Country::getCode).collect(Collectors.toList()).contains(spain.getCode()));
            serviceWorker.deleteCountry("fr");
            assertTrue(!serviceWorker.getCountries("eu").stream().map(Country::getCode).collect(Collectors.toList()).contains(spain.getCode()));

        }

        @Test
        public void testModification() {

            Country countryvalues=  new Country("Maroc","ma","MAD","Salam",new Continent("Africa","af"));

            String countrytoadd = "ma,Maroc,MAD,Salam,af";
            serviceWorker.updateCountry("fr",countrytoadd);
            Country actualCountry = serviceWorker.getCountryInfos("ma");

            assertEquals(countryvalues.getCode(),actualCountry.getCode());
            assertEquals(countryvalues.getName(),actualCountry.getName());
            assertEquals(countryvalues.getDevise(),actualCountry.getDevise());
            assertEquals(countryvalues.getGreetings(),actualCountry.getGreetings());
            assertEquals(countryvalues.getContinent().getCode(),actualCountry.getContinent().getCode());
            assertEquals(countryvalues.getContinent().getName(),actualCountry.getContinent().getName());

        }

        @Test
        public void testListerContinent() {

            Country france = serviceWorker.getCountryInfos("fr");
            Country germany = serviceWorker.getCountryInfos("es");
            Country england = serviceWorker.getCountryInfos("en");
            Country spain = serviceWorker.getCountryInfos("de");

            List<String> europeCountryList =  serviceWorker.getCountries("eu").stream().map(Country::getCode).collect(Collectors.toList());

            assertTrue(europeCountryList.contains(france.getCode()));
            assertTrue(europeCountryList.contains(germany.getCode()));
            assertTrue(europeCountryList.contains(spain.getCode()));
            assertTrue(europeCountryList.contains(england.getCode()));
            assertTrue(europeCountryList.size()==4);

        }
    }