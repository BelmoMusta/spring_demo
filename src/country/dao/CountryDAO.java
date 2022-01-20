package country.dao;

import country.model.Country;

public interface CountryDAO {
	public void displayCountry();
	public void addCountry(Country country2);
	public void displayByCode(String code);
	public void deletByCode(String code);
}
