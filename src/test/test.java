package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import country.configuration.HibernateConfiguration;
import country.dao.CountryDAO;
import country.model.Country;
import country.service.IServiceWorker;

public class test {
	ApplicationContext applicationContext = new AnnotationConfigApplicationContext(HibernateConfiguration.class);
	CountryDAO countryDao = applicationContext.getBean(CountryDAO.class);
	IServiceWorker serviceWorker = applicationContext.getBean(IServiceWorker.class);

	@Test
	public void addCountry() {

		Country c = new Country();
		c.setCode("jp");
		c.setName("japan");
		c.setDevise("won");
		c.setGreetings("Ohayu");
		c.setContinent("Asia");
		countryDao.addCountry(c);

		assertEquals(countryDao.getByCode("jp").getName(), "japan");
		assertEquals(countryDao.getByCode("jp").getCode(), "jp");
		assertEquals(countryDao.getByCode("jp").getDevise(), "won");
		assertEquals(countryDao.getByCode("jp").getGreetings(), "Ohayu");

	}

	@Test
	public void getByCode() {

		assertEquals(countryDao.getByCode("fr").getName(), "France");
		assertEquals(countryDao.getByCode("fr").getCode(), "fr");
		assertEquals(countryDao.getByCode("fr").getDevise(), "EURO");
		assertEquals(countryDao.getByCode("fr").getGreetings(), "Bonjour");

	}

	@Test
	public void DeleteCountry() {
		int s = countryDao.getCountries().size();
		countryDao.deleteCountry("fr");
		assertEquals(countryDao.getCountries().size(), s - 1);

	}

	@Test
	public void updateCountries() {

		Country c = new Country();
		c.setCode("ma");
		c.setName("moroco");
		c.setDevise("mad");
		c.setGreetings("salam");
		c.setContinent("Africa");
		countryDao.updateCountry("fr", c);

		assertEquals(countryDao.getByCode("ma").getName(), "moroco");
		assertEquals(countryDao.getByCode("ma").getCode(), "ma");
		assertEquals(countryDao.getByCode("ma").getDevise(), "mad");
		assertEquals(countryDao.getByCode("ma").getGreetings(), "salam");
		assertEquals(countryDao.getByCode("ma").getContinent(), "Africa");

	}

	@Test
	public void ListOfCountriesByContinent() {

		int s = countryDao.getCountriesByContinent("Europe").size();
		assertEquals(s, 4);

	}

}
