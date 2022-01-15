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

import country.dao.CountryDAO;
import country.dao.CountryDAOImpl;
import country.model.Country;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CountryDAOImpl.class})
public class CountryTest {

	@Autowired
	private CountryDAO countryDAO;
    @Before
    public void setup(){
//        ctx = new ClassPathXmlApplicationContext("beans/*.xml");
//        countryDAO = ctx.getBean(CountryDAO.class);
    }
    
    @Test
    public void shouldDealWithCountryByCode() {
    	final Country expected = new Country("France", "fr", "EURO","Bonjour", "eur");
    	expected.setId(1);
    	final Country result = countryDAO.getByCode("fr");
    	System.out.println(result);
    	assertEquals(expected, result);
    }
	@Test
	public void shouldDealWithSaveCountry() {
		//Arrange		
		final Country expected;
		final Country result;
		//act
		result = countryDAO.saveCountry(new Country("maroc","ma","mad","Salam!","afr"));
		expected = countryDAO.getByCode("ma");
		assertEquals(expected, result);
	}
	@Test
	public void shouldDealWithDeleteCountry() {			
		countryDAO.deleteCountry("fr");
		final Country expected = countryDAO.getByCode("fr");
//		final Country result = new Country("France", "fr", "EURO","Bonjour", "eur");
		assertThat(expected).isEqualTo(null);		
	}
	@Test
	public void shouldDealWithUpdateCountry() {
		final Country country = new Country("franca","ma","mad","Salam!","afr");
		final Country result = countryDAO.updateCountry(country, "ma");
		assertEquals(country, result);
	}
	@Test
	public void shouldDealWithContinents() {
		final List<Country> expectedList= new ArrayList<>();
			expectedList.add(new Country(1,"France", "fr", "EURO","Bonjour", "eur"));			
			expectedList.add(new Country(2,"Spain", "es", "EURO","Hola", "eur"));
			expectedList.add(new Country(3,"England", "en", "GBP","Hello", "eur"));
			expectedList.add(new Country(4,"Germany", "de", "EURO","Hallo", "eur"));
		final List<Country> List = countryDAO.continentCountries("eur");
		
		assertEquals(expectedList, List);
	}
}
