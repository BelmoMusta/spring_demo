package country.tests;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import country.config.HBconfiguration;
import country.dao.CountryDAO;
import country.model.Country;

@RunWith(Parameterized.class)
public class AddNewCountryTEST {
	static AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
			HBconfiguration.class);
	static CountryDAO countryDAO = applicationContext.getBean(CountryDAO.class);

	private int IDcountry;

	public AddNewCountryTEST(Integer value) {
		this.IDcountry = value;

	}

	@Parameters
	public static Collection<Object[]> RotournedData() {

		Object[][] data = new Object[][] {
				{ countryDAO.EditCountryInfos("fr", new Country("Algérie", "alg", "dinar", "salam", "af")) },
				{ countryDAO.AddNewCountry(new Country("Suide", "sd", "Krona", "hej", "eu")) },
				{ countryDAO.AddNewCountry(new Country("Chine", "ch", "RMB", "Niho", "as")) } };

		return Arrays.asList(data);

	}

	@Test(timeout = 10)
	public void testAddCountry() {
		assertTrue("Id du pays ajouté supérieur strictement à 0?", (int) IDcountry > 0);
	}

}
