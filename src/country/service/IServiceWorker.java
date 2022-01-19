package country.service;

import country.model.Country;

import java.util.List;

public interface IServiceWorker {
	void dealWithCountryByCode(String language);
	void addNewCountry(String info);
	Country getCountryInformations(String countryCode);
	void deleteCountry(String countryCode);
	void updateCountry(String countryCode, String newData);
	List<Country> getCountriesByContinent(String continentCode);
}
