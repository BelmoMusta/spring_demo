package country.service;

import country.model.Country;

import java.util.List;

public interface IServiceWorker {
	void dealWithCountryByCode(String language);
	void addCountry(String info);
	Country getInformations(String countryCode);
	void deleteCountry(String countryCode);
	void updateCountry(String countryCode, String newFields);
	List<Country> getCountries(String continentCode);
}
