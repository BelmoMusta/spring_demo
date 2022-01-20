package country.dao;

import country.model.Country;

import java.util.List;

public interface CountryDAO {
	Country getByCode(String code);
	void save(Country country);
	void deleteCountry(Country country);
	void updateCountry(Country country);
	List<Country> getAllCountries(String code);
}
