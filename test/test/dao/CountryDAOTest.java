package test.dao;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import app.dao.CountryDAO;
import app.entity.Continent;
import app.entity.Country;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans/database-config.xml")
public class CountryDAOTest {
	@Autowired
	private CountryDAO countryDAO ;
	
	
	@Test
	public void testGetCountry() {
		Country expectedCountry = new Country();
		expectedCountry.setCode("fr");
		expectedCountry.setName("France");
		expectedCountry.setDevise("EURO");
		expectedCountry.setGreetings("Bonjour");		
		Continent continent = new Continent();
		continent.setCode("EU");
		continent.setName("Europe");
		expectedCountry.setContinent(continent);
		assertEquals(expectedCountry.getName(),countryDAO.getByCode("fr").getName());
	}
	@Test
	public void testAddCountry() {
		Country addedCountry = new Country();
		addedCountry.setId(1);
		addedCountry.setCode("ch");
		addedCountry.setDevise("yen");
		addedCountry.setGreetings("nihao");
		addedCountry.setName("China");
		
		assertTrue( countryDAO.addCountry(addedCountry).equals(addedCountry));
	}
	@Test
	public void testDeleteCountry() {
		countryDAO.deleteCountry("mr");
		assertTrue(countryDAO.getByCode("mr")==null);
	}
	@Test
	public void testUpdateCountry() {
		Country countryUpdated = countryDAO.getByCode("fr");
		countryUpdated.setDevise("usd");
		assertTrue( countryDAO.updateCountry(countryUpdated).equals(countryUpdated));
	}
	@Test
	public void testCountriesByContinent() {
		List<Country> europ = Arrays.asList(countryDAO.getByCode("fr"));
		List<Country> europe = countryDAO.getCountriesByContinent("EU");
		
		for (int i = 0 , j = 0; i < europ.size() && j < europe.size(); i++ , j++) {
			assertTrue(europ.get(i).getId().equals(europe.get(j).getId()));
			assertTrue(europ.get(i).getName().equals(europe.get(j).getName()));
			assertTrue(europ.get(i).getCode().equals(europe.get(j).getCode()));
			assertTrue(europ.get(i).getGreetings().equals(europe.get(j).getGreetings()));
			assertTrue(europ.get(i).getDevise().equals(europe.get(j).getDevise()));
			assertTrue(europ.get(i).getContinent().getId().equals(europe.get(j).getContinent().getId()));
			assertTrue(europ.get(i).getContinent().getName().equals(europe.get(j).getContinent().getName()));
			assertTrue(europ.get(i).getContinent().getCode().equals(europe.get(j).getContinent().getCode()));
		}
		
	}

}
