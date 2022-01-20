package test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import country.dao.CountryDAO;
import country.model.Continent;
import country.model.Country;

@Transactional

public class TestClass {
@Autowired
CountryDAO countryDAO;
	@Test
	public void AjouterCountry() {
		
		Country country=new Country();
		Continent continent=new Continent();
		
		
		continent.setCode("eur");
		continent.setName("Europe");
	   
	   country.setCode("fr");
	   country.setName("France");
	   country.setDevise("EURO");
	   country.setGreetings("Bonjour");
	   country.setContinent(continent);
	   
	   int c=countryDAO.AjouterCountry(country, continent.getName());
	   assertNotEquals(c,0);
	}
	@Test
	public void dealWithCountryByCodee(){
		assertThat(countryDAO.getByCode("fr")).isNotNull();
		
	}
    @Test
    public void supprimerCountryByCode() {
	int c=countryDAO.supprimerByCode("fr");
	assertNotEquals(c,0);
    }
	@Test
	public void modifierCountry() {
	   Country country=new Country();
	   
	   country.setCode("fr");
	   country.setName("France");
	   country.setDevise("EURO");
	   country.setGreetings("Bonjour");
	   int c=countryDAO.modifierByCode(country, "france", "Europe");
	   assertNotEquals(c,0);
	}
    @Test
    public void ListerCountriesOfContinent(){
	assertThat(countryDAO.getCountrieByCode("eur")).isNotNull();
    }
}
