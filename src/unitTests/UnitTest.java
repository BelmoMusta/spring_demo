package unitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import country.configuration.*;
import country.dao.CountryDAO;
import country.model.Continent;
import country.model.Country;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Configuration.class)
public class UnitTest {
	
	@Autowired
	@Qualifier("hibernate")
	CountryDAO countryDAO;
	
	@Test
	public void shouldGetCountryByCode() {
		Country myCountry = new Country();
		myCountry.setName("Canada");
		myCountry.setCode("ca");
		myCountry.setDevise("Dollar");
		myCountry.setGreetings("Hi");		
		
	
		assertEquals(myCountry,countryDAO.getByCode("ca"));
	}
	
	@Test
	public void shoudDeleteCountryByCode() {
		assertTrue(countryDAO.deleteCountryByCode("mar")==1);
		assertNull(countryDAO.getByCode("mar")); 		
	}
	
	
	
	@Test
	public void shouldGetContinentByName() {
		Continent myContinent = new Continent();
		myContinent.setName("Afrique");
		myContinent.setCode("af");
		
		assertEquals(myContinent,countryDAO.getContinentByName("Afrique"));
	}
	
	@Test
	public void shouldGetContinentByCode() {
		Continent myContinent = new Continent();
		myContinent.setName("Afrique");
		myContinent.setCode("af");
		
		assertEquals(myContinent,countryDAO.getContinentByCode("af"));
	}
	
	@Test
	public void shouldListCountriesByContinent() {
		Country myCountry = new Country();
		myCountry.setName("Canada");
		myCountry.setCode("ca");
		myCountry.setDevise("Dollar");
		myCountry.setGreetings("Hi");	
		
		List<Country> myList=new ArrayList<Country>();
		myList.add(myCountry);
		
		assertEquals(myList,countryDAO.listCountriesByContinent("am"));
	}
	
	@Test
	public void shouldUpdateCountry() {
		Country myCountry = new Country();
		myCountry.setName("Maroc");
		myCountry.setCode("mar");
		myCountry.setDevise("DH");
		myCountry.setGreetings("Salam Alaykom");
		
		assertTrue(countryDAO.updateCountry("mar", myCountry, "Afrique")==1);
		assertEquals(countryDAO.getByCode("mar"), myCountry);
	}
	
	@Test
	public void shouldInsertCountry() {
		Country myCountry = new Country();
		myCountry.setName("Senegal");
		myCountry.setCode("sen");
		myCountry.setDevise("CFA");
		myCountry.setGreetings("nan-ga-def");
		
		assertTrue(countryDAO.insert(myCountry, "Afrique")==1);
		assertNotNull(countryDAO.getByCode("sen"));
		assertEquals(countryDAO.getByCode("sen"), myCountry);
	}
	
	
	
	
}
