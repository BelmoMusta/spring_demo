package Test;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import country.configuration.*;
import country.dao.CountryDAO;
import country.model.Continent;
import country.model.Country;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = configuration.class)

public class TestFonctionnel {
	@Autowired
	@Qualifier("hibernate")
	CountryDAO countryDAO;
	Country _couuntry = new Country();
	Continent _continent = new Continent();
	@Test
	public void testCountryByCode() {
		_couuntry.setCode("de");
		_couuntry.setName("Germany");
		_couuntry.setGreetings("Halo");	
		_couuntry.setDevise("EURO");
			
		assertEquals(_couuntry,countryDAO.getByCode("de"));
	}
	@Test
	public void testContinentByCode() {
		_continent.setCode("am");
		_continent.setName("America");
		
		assertEquals(_continent,countryDAO.getContinentByCode("am"));
	}

}

