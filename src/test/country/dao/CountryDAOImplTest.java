package test.country.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import country.configuration.ConfigurationHibernate;
import country.dao.CountryDAO;
import country.model.Country;
import country.service.IServiceWorker;

public class CountryDAOImplTest {
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConfigurationHibernate.class);
    CountryDAO countryDAO = applicationContext.getBean(CountryDAO.class);
    IServiceWorker serviceWorker = applicationContext.getBean(IServiceWorker.class);
    List<Country> dataBase = new ArrayList<>();

    @Test
    public void getByCode() {
        dataBase = countryDAO.getAllCountries();
        assertEquals("meme nom", countryDAO.getCountryByCode("fr").getName(), "France");
        assertEquals("meme devise", countryDAO.getCountryByCode("fr").getDevise(),
                "EURO");
        assertEquals("meme greetings", countryDAO.getCountryByCode("fr").getGreetings(),
                "Bonjour");
        dataBase.clear();
    }

    @Test
    public void addCoutry() {
        dataBase = countryDAO.getAllCountries();
        Country country = serviceWorker.getContryFromData("ma,maroc,af,dirham,salam");
        countryDAO.addCountry(country);
        assertEquals("ajouter avec success", countryDAO.getAllCountries().size(), dataBase.size() + 1);
        dataBase.clear();
    }

    @Test
    public void removeCountry() {
        dataBase = countryDAO.getAllCountries();
        countryDAO.removeCountry("fr");
        assertEquals("supprimer avec success", countryDAO.getAllCountries().size(), dataBase.size() - 1);
        dataBase.clear();
    }

    @Test
    public void updateCountry() {
        dataBase = countryDAO.getAllCountries();
        // "fr,France,eu,EURO,Bonjour"
        Country updatedCountry = serviceWorker.getContryFromData("fr,France,eu,DOLLAR,Salut");
        assertEquals("meme nom", countryDAO.getCountryByCode("fr").getName(), "France");
        assertEquals("meme devise", countryDAO.getCountryByCode("fr").getDevise(),
                "EURO");
        assertEquals("meme greetings", countryDAO.getCountryByCode("fr").getGreetings(), "Bonjour");
        countryDAO.updateByCode("fr", updatedCountry);
        assertEquals("meme devise", countryDAO.getCountryByCode("fr").getDevise(),
                "DOLLAR");
        assertEquals("meme greetings", countryDAO.getCountryByCode("fr").getGreetings(),
                "Salut");
        dataBase.clear();
    }

    @Test
    public void listAllCoutriesInContinent() {
        dataBase = countryDAO.getAllCountries();

        assertEquals("la list des pays", countryDAO.getAllCountriesInContinent("eu").size(), 3);
        dataBase.clear();
    }
}
