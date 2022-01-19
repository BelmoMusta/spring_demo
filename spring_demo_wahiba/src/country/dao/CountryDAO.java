package country.dao;

import country.model.Country;

import java.util.List;

public interface CountryDAO {
	Country getByCode(String code);

	void saveCountry(Country country);

	void deleteCountry(Country country);

	void updateCountry(Country country);

	List<Country> getAllCountriesByContinent(String continentCode);
}
