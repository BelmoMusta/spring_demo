package test;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import country.dao.CountryDAO;
import country.service.IServiceWorker;
import country.model.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.List;
import java.util.ArrayList;


public class test {
ApplicationContext applicationContext =new ClassPathXmlApplicationContext("beans/*.xml");
CountryDAO countryDao = applicationContext.getBean(CountryDAO.class);
IServiceWorker serviceWorker = applicationContext.getBean(IServiceWorker.class);
List<Country> DB_result =new ArrayList<>();
List<Continent> DB_result2 =new ArrayList<>();



@Test
public void addCountry()
{
	DB_result=countryDao.getCountries();
	Country c=new Country();
	c.setCode("jp");
	c.setName("japan");
	c.setDevise("won");
	c.setGreetings("Ohayu");
	c.setContinent("Asia");
	countryDao.addCountry(c);
	int s=countryDao.getCountries().size();
	assertSame(countryDao.getByCode("jp").getName(),DB_result.get(s-1).getName());
	assertSame(countryDao.getByCode("jp").getCode(),DB_result.get(s-1).getCode());
	assertSame(countryDao.getByCode("jp").getDevise(),DB_result.get(s-1).getDevise());
	assertSame(countryDao.getByCode("jp").getGreetings(),DB_result.get(s-1).getGreetings());
	   
	  DB_result.clear();
}


@Test
public void getByCode() {
	
	DB_result=countryDao.getCountries();
   assertSame(countryDao.getByCode("fr").getName(),DB_result.get(0).getName());
   assertSame(countryDao.getByCode("fr").getCode(),DB_result.get(0).getCode());
   assertSame(countryDao.getByCode("fr").getDevise(),DB_result.get(0).getDevise());
   assertSame(countryDao.getByCode("fr").getGreetings(),DB_result.get(0).getGreetings());
   
   DB_result.clear();
}

@Test
public void DeleteCountry() {
	DB_result=countryDao.getCountries();
	countryDao.deleteCountry("fr");
	int s=countryDao.getCountries().size();
	assertSame(s,DB_result.size()-1);
	
	  DB_result.clear();
}

@Test
public void updateCountries() {
	DB_result=countryDao.getCountries();
	Country c=new Country();
	c.setCode("ma");
	c.setName("moroco");
	c.setDevise("mad");
	c.setGreetings("salam");
	c.setContinent("Africa");
	countryDao.updateCountry("fr", c);
	assertSame(countryDao.getByCode("ma").getName(),DB_result.get(0).getName());
	assertSame(countryDao.getByCode("ma").getCode(),DB_result.get(0).getCode());
	assertSame(countryDao.getByCode("ma").getDevise(),DB_result.get(0).getDevise());
	assertSame(countryDao.getByCode("ma").getGreetings(),DB_result.get(0).getGreetings());
	   
	 DB_result.clear();
}

@Test
public void ListOfCountriesByContinent() {
	DB_result2=countryDao.getAllCountinents();
	List<Country> list = countryDao.getCountriesByContinent("Europe");
	int s=countryDao.getAllCountinents().size();
	assertSame(s,DB_result.size());
	
	
}

}
