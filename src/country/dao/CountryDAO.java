package country.dao;

import country.model.Country;

public interface CountryDAO {
	Country getByCode(String code);
	void addCountry(Country country);
	Country modifyCountry(String code,Country c);
	void deleteCountry(String code);
}
