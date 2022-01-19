package country.dao;

import java.util.List;

import country.model.Country;

public interface CountryDAO {
	Country getByCode(String code);
	boolean addCountry(Country country, String continentCode);
	boolean deleteCountry(String countryCode);
	boolean updateCountry(String countryCode, Country newCountryInfos);
	List<Country> getCountriesOfContinent(String continentCode);
	void initData();
}
