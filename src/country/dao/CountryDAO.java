package country.dao;

import java.util.List;

import country.model.Country;

public interface CountryDAO {
	Country getByCode(String code);
	Country saveCountry(Country c);
	void deleteCountry(String code);
	Country updateCountry(Country c, String code);
	List<Country> continentCountries(String code);
}
