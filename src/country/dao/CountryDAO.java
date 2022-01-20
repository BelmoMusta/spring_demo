package country.dao;

import org.springframework.stereotype.Component;

import country.model.Country;


public interface CountryDAO {
	
	public void saveCountry(Country country);
	public void listCountry();
	public void findByCode(String code);
	public void deleteByCode(String code);
	public void updateByCode(String code, Country contry);
}
