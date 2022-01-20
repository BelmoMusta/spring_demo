package Test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import country.dao.CountryDAO;
import country.model.Country;
import country.service.IServiceWorker;

@Transactional
public class TestAPP {
	 ApplicationContext applicationContext =new ClassPathXmlApplicationContext("beans/*.xml");
	    IServiceWorker serviceWorker = applicationContext.getBean(IServiceWorker.class);

	@Autowired
	CountryDAO countryDAO;
	
	@Test
	public void addNewContryTest() {
		
		Country newCountry=new Country ();
		newCountry.setName("Italie");
		newCountry.setCode("it");
		newCountry.setDevise("Euro");
		newCountry.setGreetings("Ciao");
		newCountry.setContinentCode("eu");
		
		String country ="Italie,it,Euro,Ciao,eu";
		serviceWorker.addNewCountry(country);
		Country addedCountry = serviceWorker.getCountryInformations("it");
		assertEquals(newCountry.getName(),addedCountry.getName());
		assertEquals(newCountry.getCode(),addedCountry.getCode());
		assertEquals(newCountry.getDevise(),addedCountry.getDevise());
		assertEquals(newCountry.getGreetings(),addedCountry.getGreetings());
		assertEquals(newCountry.getContinentCode(),addedCountry.getContinentCode());
		
	}
	
	
	@Test
	public void afficheCountryTest() {
		
		Country country = serviceWorker.getCountryInformations("de");
		assertEquals("Germany",country.getName());
		assertEquals("de",country.getCode());
		assertEquals("EURO",country.getDevise());
		assertEquals("Halo",country.getGreetings());
		assertEquals("eu",country.getContinentCode());
	}
	
	@Test 
	public void deleteCountryTest() {
		int sizeBefore =serviceWorker.continentCountriesByCode("eu").size();
		serviceWorker.deleteCountryByCode("fr");
		int sizeAfter=serviceWorker.continentCountriesByCode("eu").size();
		assertEquals(sizeBefore,sizeAfter+1);
	}
	
	@Test
	public void modifyCountryTest() {
		serviceWorker.addNewCountry("Italie,it,Euro,Ciao,eu");
		String newModifCountry = "Italia,it,EURO,Hola,eu";
		serviceWorker.ModifCountryByCode("it",newModifCountry );
		Country modifyCountry = serviceWorker.getCountryInformations("it");
		assertEquals("Italia",modifyCountry.getName());
		assertEquals("it",modifyCountry.getCode());
		assertEquals("EURO",modifyCountry.getDevise());
		assertEquals("Hola",modifyCountry.getGreetings());
		assertEquals("eu",modifyCountry.getContinentCode());
	}
	
	@Test
	public void continentCountryTest() {
		
		List <String> expectCountries=new ArrayList<String>() ;
		
		expectCountries.add("France");
		expectCountries.add("Spain");
		expectCountries.add("England");
		expectCountries.add("Germany");
		//List<String> countries=serviceWorker.continentCountriesByCode("eu");
		
		assertEquals(expectCountries,serviceWorker.continentCountriesByCode("eu"));
	}
}
