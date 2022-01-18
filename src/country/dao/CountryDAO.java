package country.dao;

import country.model.Country;

public interface CountryDAO {

	public void enregistrerCountry(Country country);
	public void listCountry();
	public void findByCode(String code);
	
}

