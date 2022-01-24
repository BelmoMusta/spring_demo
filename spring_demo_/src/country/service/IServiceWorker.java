package country.service;

import country.model.Country;

import java.util.List;

public interface IServiceWorker {
	void dealWithCountryByCode(String language);

	void addCountry(String country);

    Country getCountryData(String code);

	void deleteCountry(String code);

	void updateCountry(String countryCode, String newFields);

	List<Country> getCountriesByContinent(String continentCode);
}
