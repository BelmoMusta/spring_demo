package tests;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import country.dao.CountryDAO;
import country.model.Country;

public class TestCountryDAO {
	private CountryDAO countryDAO;
	private ApplicationContext appContext;

	@Before
	public void setUp() throws Exception {
		appContext = new AnnotationConfigApplicationContext("country");
		countryDAO = (CountryDAO) appContext.getBean(CountryDAO.class);
	}

	@After
	public void tearDown() throws Exception {

		((AbstractApplicationContext) appContext).close();
	}

	@Test
	public void testAddCountry() {
		Country country = new Country();
		country.setName("Maroc");
		country.setCode("ar");
		country.setDevise("DH");
		country.setGreetings("salam");
		int response = countryDAO.add(country, "Africa");
		assertTrue(response > 0);
	}

	@Test
	public void testSelectCountryByCode() {
		Country country = countryDAO.getByCode("fr");
		assertNotNull(country);
	}

	@Test
	public void testDeleteCountryByCode() {
		int response = countryDAO.deleteByCode("fr");
		assertTrue(response > 0);
	}

	@Test
	public void testUpdateCountryByCode() {
		Country country = new Country();
		country.setName("Maroc");
		country.setCode("ar");
		country.setDevise("DH");
		country.setGreetings("salam");
		int response = countryDAO.updateByCode(country, "fr", "Europe");
		assertTrue(response > 0);
	}

	@Test
	public void testSelectCountriesOfContinent() {
		List<Country> countriesOfEurope = new ArrayList<>();
		countriesOfEurope.add(countryDAO.getByCode("fr"));
		countriesOfEurope.add(countryDAO.getByCode("es"));
		countriesOfEurope.add(countryDAO.getByCode("en"));
		countriesOfEurope.add(countryDAO.getByCode("de"));
		assertArrayEquals(countriesOfEurope.toArray(), countryDAO.getCountrieByCode("eu").toArray());
	}
}
