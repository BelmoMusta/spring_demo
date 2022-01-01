package country.dao;

import java.util.List;

import country.model.Country;

public interface CountryDAO {
	Country getByCode(String code);
	Country addCountry(Country country);
	Country getCountry(String code);
	List<Country> getCountries();
	void deleteCountry(String code);
	Country updateCountry(Country country , String code);
	List<Country> getCountriesByContinent(String continent);
}
