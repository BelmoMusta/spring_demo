package Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import country.dao.CountryDAO;
import country.model.Continent;
import country.model.Country;

@Transactional

public class TestCountry {
	CountryDAO countryDAO;
	@Test
	public void addCountry() {
		Continent continent=new Continent();
		continent.setCode("am");
		continent.setName("america");
	   Country country=new Country();
	   country.setName("Maroc");
	   country.setCode("ar");
	   country.setDevise("DH");
	   country.setGreetings("salam");
	   country.setContinent(continent);
	   countryDAO.saveCountry(country);//.add(country, continent.getName());
	   assertNotEquals(country,0);
	}
	@Test
	public void SelectCountryByCode(){
		assertThat(countryDAO.getByCode("fr")).isNotNull();
		
	}
    @Test
    public void deleteCountryByCode() {
    	final Country c;
    	countryDAO.deleteCountry("fr");
    	c=countryDAO.getByCode("fr");
    	assertThat(c).isEqualTo(null);
    }
	@Test
	public void updateCountryByCode() {
	   Country country=new Country();
	   country.setName("Maroc");
	   country.setCode("mr");
	   country.setDevise("DH");
	   country.setGreetings("salam");
	   countryDAO.update(country);
	   assertNotEquals(country,null);
	}
    @Test
    public void selectCountryOfContinent(){
	assertThat(countryDAO.getContinentByCode("afr")).isNotNull();
    }
}