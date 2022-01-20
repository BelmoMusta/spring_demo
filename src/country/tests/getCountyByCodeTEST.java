package country.tests;

import static org.junit.Assert.assertEquals;

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
public class getCountyByCodeTEST {
	static AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
			HBconfiguration.class);
	static CountryDAO countryDAO = applicationContext.getBean(CountryDAO.class);;
	static Country pays = countryDAO.getCountyByCode("fr");;

	private String exceptedResult;
	private String Value;

	public getCountyByCodeTEST(String exceptedResult, String firstValue) {
		this.exceptedResult = exceptedResult;
		this.Value = firstValue;

	}

	@Parameters
	public static Collection<Object[]> RotournedData() {

		Object[][] data = new Object[][] { { "France", pays.getName() }, { "EURO", pays.getDevise() },
				{ "Bonjour", pays.getGreetings() }, { "eu", pays.getCodeContinent() } };

		return Arrays.asList(data);

	}

	@Test(timeout = 10)
	public void testgetByCode() {
		assertEquals("Name=exceptedName?Devise=exceptedDevice?...", Value, exceptedResult);
	}

}
