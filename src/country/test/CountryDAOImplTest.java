package country.test;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import country.configurationHibernate.ConfigurationHibernate;
import country.dao.CountryDAOImpl;
import country.model.Country;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ConfigurationHibernate.class})
public class CountryDAOImplTest {

	@Autowired
	CountryDAOImpl countrydao ;
	
	static boolean isInitialized = false;
	private List <Country> countrylist = new ArrayList<>();
	
	@Before
	public void setup() {
		if(!isInitialized)
		{
			countrylist=countrydao.listCountry();
			isInitialized = true;
		}
	}
	@Test
	public void enregistrerCountryTest()
	{   
		Country country =new Country();
	    country.setCode("Mr");
		country.setName("Maroc");
		country.setGreetings("Salam");
		country.setDevise("DH");
		assertEquals(true,countrydao.enregistrerCountry(country));
		
	}
	
        @Test
        public void findByCodeTest() 
        {
        	assertEquals("",countrydao.findByCode("fr").getName(),"France");
        	assertEquals("",countrydao.findByCode("fr").getGreetings(),"Bonjour");
        	assertEquals("",countrydao.findByCode("fr").getDevise(),"EURO");
        	
         }
        
        @Test
        public void supprimerByCodeTest()
    	{   String val ="es";
    		assertEquals(true,countrydao.supprimerByCode(val));
    		
    	}
        
        @Test
        public void modificationByCodeTest() {
        	String val1="de";
        	Country country =new Country();
    		country.setName("Alemand");
    		country.setGreetings("Haloo");
    		country.setDevise("EUuRO");
    		assertEquals(true,countrydao.modificationByCode(val1,country));
        	
        }
        
}
