package country.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import country.dao.CountryDAO;
import country.model.Country;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@TestExecutionListeners(DependencyInjectionTestExecutionListener.class)
public class CountryTest {

	@Autowired
	private CountryDAO countryDAO;
	
	@Test
	public void shouldDealWithSaveCountry() {
		//Arrange
//		final String param = "maroc,ma,EURO,Salam!,afr";
		Country countryM;
		final Country expected;
		//act
		countryM = new Country("maroc","ma","EURO","Salam!","afr");
		final Country result = countryDAO.saveCountry(countryM);
		expected = countryDAO.getByCode("ma");
		assertEquals(expected, result);
	}

}
