package country.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import country.dao.CountryDAO;
import country.model.Country;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/beans/database-config.xml","/beans/hibernate-config.xml"})
public class CountryDAOTest {
	
	@Autowired
	CountryDAO countryDAO;
	@Test
	public void testGetCountry() {
		Country expectedCountry = new Country();
		expectedCountry.setId(1);
		expectedCountry.setCode("fr");
		expectedCountry.setDevise("EURO");
		expectedCountry.setGreetings("Bonjour");
		expectedCountry.setName("France");
		assertEquals(expectedCountry,countryDAO.getByCode("fr"));
	}
	@Test
	public void testAddCountry() {
		Country addedCountry = new Country();
		addedCountry.setCode("rsa");
		addedCountry.setDevise("rubel");
		addedCountry.setGreetings("kharachov");
		addedCountry.setName("russia");
		addedCountry.setId(9);
		assertTrue(countryDAO.addCountry(addedCountry) && countryDAO.getByCode("rsa").equals(addedCountry));
	}
	@Test
	public void testDeleteCountry() {
		assertTrue(countryDAO.deleteCountry("en") && countryDAO.getByCode("en")==null);
	}
	@Test
	public void testUpdateCountry() {
		Country countryToBeUpdated = countryDAO.getByCode("es");
		countryToBeUpdated.setDevise("other");
		assertTrue(countryDAO.updateCountry("es", countryToBeUpdated) && countryDAO.getByCode("es").equals(countryToBeUpdated));
	}
	@Test
	public void testGetCountriesOfContinent() {
		List<Country> countriesOfAfrica = new ArrayList<Country>();
		countriesOfAfrica.add(countryDAO.getByCode("ma"));
		countriesOfAfrica.add(countryDAO.getByCode("eg"));
		assertEquals(countriesOfAfrica, countryDAO.getCountriesOfContinent("af"));
	}
}
