package country.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import country.config.Config;
import country.dao.CountryDAO;
import country.model.Country;

@ContextConfiguration(classes = {Config.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class ServiceWorkerTest {
	
	@Autowired(required = true)
	private CountryDAO countryDAO;
	
	@Before
	public void setup() {
		
	}
	
	@Test
	public void testgetCountryByCode() {
		Country c = new Country(2,"Spain", "es", "EURO", "Hola", "Eur");
		assertEquals(c.getCode(),countryDAO.getByCode("es").getCode());
	}
	
	@Test
	public void testSaveCountry() {
		Country c = new Country("japon","ja","YEN","Konishiwa","Asi");
		countryDAO.SaveCountry(c);
		assertEquals(c.getDevise(),countryDAO.getByCode("ja").getDevise());
		assertEquals(c.getGreetings(),countryDAO.getByCode("ja").getGreetings());
		assertEquals(c.getCode_cont(),countryDAO.getByCode("ja").getCode_cont());
		assertEquals(c.getCode(),countryDAO.getByCode("ja").getCode());
		assertEquals(c.getName(),countryDAO.getByCode("ja").getName());
	}
	
	@Test
	public void testDeleteCountry() {
		countryDAO.DeleteCountry("en");
		assertEquals(countryDAO.getByCode("en"),null);
	}
	
	@Test
	public void testUpdateCountry() { 
		Country c = new Country("japon","ja","YEN","Ohayo","Asi");
		countryDAO.UpdateCountry("ja", c);
		assertEquals(c.getDevise(),countryDAO.getByCode("ja").getDevise());
		assertEquals(c.getGreetings(),countryDAO.getByCode("ja").getGreetings());
		assertEquals(c.getCode_cont(),countryDAO.getByCode("ja").getCode_cont());
		assertEquals(c.getCode(),countryDAO.getByCode("ja").getCode());
		assertEquals(c.getName(),countryDAO.getByCode("ja").getName());
	}
	
	@Test
	public void testListCountries() {
		List<Country>l=new ArrayList<Country>();
		l.add(new Country(1,"France", "fr", "EURO", "Bonjour", "Eur"));
		l.add(new Country(2,"Spain", "es", "EURO","Hola", "Eur"));
		l.add(new Country(3,"England", "en", "GBP","Hello", "Eur"));
		l.add(new Country(4,"Germany", "de", "EURO","Hallo", "Eur"));
		
		
		List<Country>listeCountries=countryDAO.ListCountries("Eur");
		for(int i=0;i<l.size();i++) {
			assertEquals(l.get(i).getDevise(),listeCountries.get(i).getDevise());
			assertEquals(l.get(i).getGreetings(),listeCountries.get(i).getGreetings());
			assertEquals(l.get(i).getCode_cont(),listeCountries.get(i).getCode_cont());
			assertEquals(l.get(i).getCode(),listeCountries.get(i).getCode());
			assertEquals(l.get(i).getName(),listeCountries.get(i).getName());
		}
		
	}

}
