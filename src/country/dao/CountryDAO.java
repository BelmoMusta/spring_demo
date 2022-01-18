package country.dao;


import java.util.List;

import country.model.Country;

public interface CountryDAO {
	Country getByCode(String code);
	void addCountry(Country c );
	void deleteCountry(String code);
	void getAllCountries();
	void updateCountry(String Code , Country c);
}
