package country.dao;


import java.util.List;

import country.model.*;

public interface CountryDAO {
	Country getByCode(String code);
	void addCountry(Country c );
	void deleteCountry(String code);
	List<Country> getCountries();
	void updateCountry(String Code , Country c);
	List<Continent> getAllCountinents();
	List<Country> getCountriesByContinent(String continent);
	
}
