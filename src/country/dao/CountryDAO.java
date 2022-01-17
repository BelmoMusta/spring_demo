package country.dao;

import country.model.Country;

public interface CountryDAO {
	Country getByCode(String code);
	void setNewCountry(Country country);
	void deleteCountry(Country country);
	void updateCountry(Country country);

}
