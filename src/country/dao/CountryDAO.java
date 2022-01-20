package country.dao;

import country.model.Country;

public interface CountryDAO {
	void displayCountry();
	void addCountry(Country country2);
	public void displayByCode(String code);
}
