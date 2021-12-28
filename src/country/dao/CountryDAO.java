package country.dao;

import country.model.Country;

public interface CountryDAO {
	Country getByCode(String code);
	Country saveCountry(Country c);
	void deleteCountry(String code);
}
