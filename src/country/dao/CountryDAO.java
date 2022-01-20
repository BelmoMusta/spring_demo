package country.dao;

import country.model.Country;

public interface CountryDAO {
	Country getByCode(String code);
	void addCountry(Country country,String nomContinet);
	void deleteCountry(String code);
	void updateCountry(String code, Country country, String nomContinet);
	boolean exist(String code);
}
