package country.dao;

import java.util.List;

import country.model.Country;

public interface CountryDAO {
	Country getCountryByCode(String code);

	List<Country> getAllCountries();

	int addCountry(Country contryFromData);

	int removeCountry(String countryCode);

	int updateByCode(String countryCode, Country country);

	List<Country> getAllCountriesInContinent(String continentCode);

	String getContinentName(String continentCode);
}
