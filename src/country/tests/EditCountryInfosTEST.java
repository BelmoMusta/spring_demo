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
import country.service.IServiceWorker;

@RunWith(Parameterized.class)
public class EditCountryInfosTEST {
	static AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
			HBconfiguration.class);
	static IServiceWorker serviceWorker = applicationContext.getBean(IServiceWorker.class);
	private Integer exceptedResult;
	private Integer Value;

	public EditCountryInfosTEST(Integer exceptedResult, Integer firstValue) {
		this.exceptedResult = exceptedResult;
		this.Value = firstValue;

	}

	@Parameters
	public static Collection<Object[]> MultyTestsData() {

		Object[][] data = new Object[][] { { 1, serviceWorker.EditCountryByCode("fr", "france,ER,salut") },
				{ 0, serviceWorker.EditCountryByCode("ess", "france,ER,salut") },
				{ 1, serviceWorker.EditCountryByCode("en", "angleterre,GBP,Hi") },
				{ 0, serviceWorker.EditCountryByCode("mrt", "france,ER,salut") } };

		return Arrays.asList(data);

	}

	@Test(timeout = 10)
	public void testEditCountryInfos() {
		assertEquals("Une seule ligne a été mise à jour ?", Value, exceptedResult);
	}

}
