package country.dao;

import country.model.Country;

import java.util.List;

public interface CountryDAO {
	Country getByCode(String code);
	Country saveCountry(Country country);
	void DeleteCountry(String code);
	void UpdateCountry(String code,Country country);
	List<Country> CountriesByContinentCode(String continentCode);
}
