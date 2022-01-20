package test;



import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import configuration.AppConfig;
import country.dao.ContinentDAO;
import country.dao.CountryDAO;
import country.model.Country;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class AppTest {
	
	ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
	
	@Autowired
    CountryDAO countryDAO;
	@Autowired
    ContinentDAO continentDAO;
	
	@Test
	public void selectCountry(){
		assertThat(countryDAO.getByCode("fr")).isNotNull();
	}
	
    @Test
    public void addCountry() {
    	Country country = new Country();
    	country.setCode("bel");
    	country.setName("Belgique");
    	country.setDevise("EURO");
    	country.setGreetings("Bonjour");
    	countryDAO.addCountry(country, "Europe");
    	country.setId(countryDAO.getByCode("bel").getId());
    	country.setContinent(countryDAO.getByCode("bel").getContinent());
    	
    	assertEquals(countryDAO.getByCode("bel").getId(), country.getId());
    	assertEquals(countryDAO.getByCode("bel").getCode(), country.getCode());
    	assertEquals(countryDAO.getByCode("bel").getDevise(), country.getDevise());
    	assertEquals(countryDAO.getByCode("bel").getGreetings(), country.getGreetings());
    }
    
    @Test
    public void deleteCountry() {
    	Country country = new Country();
    	country.setCode("alg");
    	country.setName("Algerie");
    	country.setDevise("DINAR");
    	country.setGreetings("Salam");
    	countryDAO.addCountry(country, "Afrique");
    	
    	countryDAO.deleteCountry("alg");
    	assertEquals(null, countryDAO.getByCode("alg"));
    	
    }
    
    @Test
    public void updateCountry() {
    	Country country1 = new Country();
    	country1.setCode("alg");
    	country1.setName("Algerie");
    	country1.setDevise("DINAR");
    	country1.setGreetings("Salam");
    	countryDAO.addCountry(country1, "Afrique");
    	
    	Country country2 = new Country();
    	country2.setCode("alg2");
    	country2.setName("Algerie2");
    	country2.setDevise("DINAR2");
    	country2.setGreetings("Salam2");
    	
    	countryDAO.updateCountry("alg", country2, "Afrique");
    	
    	assertEquals(countryDAO.getByCode("alg2").getCode(), country2.getCode());
    	assertEquals(countryDAO.getByCode("alg2").getDevise(), country2.getDevise());
    	assertEquals(countryDAO.getByCode("alg2").getGreetings(), country2.getGreetings());
    	
    }
    
    @Test
    public void selectCountriesOfContinent(){
	assertThat(continentDAO.getContinentByCode("eu")).isNotNull();
    }
    
}
