package country.dao;

import country.model.Country;

public interface CountryDAO {
	Country getByCode(String code);
	void addCountry(Country country);
	void deleteCountry(String code);
	//Country editCountry(String code);
	//List<Country> getCountriesByContinent(String continent);
}
