package country.dao;

import java.util.List;

import country.model.Country;

public interface CountryDAO {
	Country getByCode(String code);

	List<Country> getAllCountriesInContinent(String continentCode);

	String getContinentName(String continentCode);
}
