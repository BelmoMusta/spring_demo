package country.service;

import country.model.Country;

public interface IServiceWorker {
	void dealWithCountryByCode(String language);
	void addCountry(String country);
	void getAllCountries();
	void modifyCountryByCode(String code, Country c);
	void deleteCountryByCode(String code);
	void getContinentCountriesByCode(String code);
}
