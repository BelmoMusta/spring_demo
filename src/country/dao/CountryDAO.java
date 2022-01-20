package country.dao;

import country.model.Country;

public interface CountryDAO {

	Country getByCode(String code);

	void saveCountry(Country country);

	void deleteCountry(Country country);

	void updateCountry(Country country);
}
