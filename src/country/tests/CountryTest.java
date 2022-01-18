package country.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import static org.fest.assertions.api.Assertions.assertThat;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import country.domain.Country;
import country.repository.CountryDAO;
import country.repository.CountryDAOImpl;
import country.service.impl.ServiceDeatails;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CountryDAOImpl.class})
public class CountryTest {

	@Autowired
	private CountryDAO countryDAO;
	@Autowired
	private ServiceDeatails serviceDeatails;
    @Before
    public void setup(){
//        ctx = new ClassPathXmlApplicationContext("beans/*.xml");
//        countryDAO = ctx.getBean(CountryDAO.class);
    }
    
    @Test
    public void getByCodeTest() {
    	//Arrange attributes
    	final Country expected;    	
    	final Country result; 
    	//Act 
    	expected = new Country();
    	expected.setCode("fr");
    	expected.setName("France");
    	expected.setContinent("eur");
    	expected.setDevise("EURO");
    	expected.setGreet("Bonjour");
    	expected.setId(1);
    	result = countryDAO.getByCode("fr");
    	assertEquals(expected, result);
    }
	@Test
	public void createCountryTest() {
		//Arrange attributes		
		final Country expected;
		final Country result;
		//Act 
		expected=new Country();
		expected.setCode("ma");
    	expected.setName("maroc");
    	expected.setContinent("afr");
    	expected.setDevise("mad");
    	expected.setGreet("Salam");
    	serviceDeatails.createCountry("ma,maroc,mad,salam,afr");
		result = countryDAO.getByCode("ma");
		assertEquals(result.getName(), "maroc");
	}
	@Test
	public void updateCountryTest() {
		//Arrange attributes
		final Country expected;
		final Country result;
		//Act
		expected = new Country();
		expected.setCode("ma");
    	expected.setName("franca");
    	expected.setContinent("afr");
    	expected.setDevise("mad");
    	expected.setGreet("Salam");
    	serviceDeatails.updateCountry("ma,france,afr,mad,salam");
    	result = countryDAO.getByCode("ma");
		assertEquals("france", result.getName());
	}
	@Test
	public void loadContinentsTest() {
		final List<Country> expectedList= new ArrayList<>();
			expectedList.add(new Country(1,"France", "fr", "EURO","Bonjour", "eur"));			
			expectedList.add(new Country(2,"Spain", "es", "EURO","Hola", "eur"));
			expectedList.add(new Country(3,"England", "en", "GBP","Hello", "eur"));
			expectedList.add(new Country(4,"Germany", "de", "EURO","Hallo", "eur"));
		final List<Country> List = countryDAO.continentCountries("eur");
		
		assertEquals(expectedList, List);
	}
	@Test
	public void deleteCountryTest() {
		//Arrange attribute
				final Country expected;
				//Act
				serviceDeatails.createCountry("ro,rom,jin,rawama,inta");
				serviceDeatails.deleteCountry("ro");
				expected = countryDAO.getByCode("ro");
				assertEquals(expected, null);
	}
}
