package test.country.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import country.dao.CountryDAO;
import country.model.Country;

public class CountryDAOImplTest {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans/*.xml");
    CountryDAO countryDAO = applicationContext.getBean(CountryDAO.class);
    List<Country> dataBase = new ArrayList<>();

    @Ignore
    @Test
    public void getByCode() {
        dataBase = countryDAO.getAllCountries();
        assertEquals("meme nom", countryDAO.getByCode("fr").getName(), dataBase.get(0).getName());
        assertEquals("meme devise", countryDAO.getByCode("fr").getDevise(),
                dataBase.get(0).getDevise());
        assertEquals("meme greetings", countryDAO.getByCode("fr").getGreetings(),
                dataBase.get(0).getGreetings());
        dataBase.clear();
    }

    @Ignore
    @Test
    public void addCoutry() {
        dataBase = countryDAO.getAllCountries();
        assertEquals("meme nom", countryDAO.getByCode("fr").getName(), dataBase.get(0).getName());
        dataBase.clear();
    }

    @Ignore
    @Test
    public void removeCountry() {
        dataBase = countryDAO.getAllCountries();
        assertEquals("meme nom", countryDAO.getByCode("fr").getName(), dataBase.get(0).getName());
        dataBase.clear();
    }

    @Ignore
    @Test
    public void updateCountry() {
        dataBase = countryDAO.getAllCountries();
        assertEquals("meme nom", countryDAO.getByCode("fr").getName(), dataBase.get(0).getName());
        dataBase.clear();
    }

    @Ignore
    @Test
    public void listAllCoutriesInContinent() {
        dataBase = countryDAO.getAllCountries();
        assertEquals("meme nom", countryDAO.getByCode("fr").getName(), dataBase.get(0).getName());
        dataBase.clear();
    }
}
