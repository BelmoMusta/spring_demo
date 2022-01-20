package country.dao;

import java.util.List;

import country.model.Country;

public interface CountryDAO {
	Country getByCode(String code);
	void SaveCountry(Country c);
	void DeleteCountry(String code);
	void UpdateCountry(String lang, Country c);
	List<Country> ListCountries(String codeContinent);
}
