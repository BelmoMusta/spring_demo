package country.service;

import country.model.Country;

public interface IServiceWorker {
	void dealWithCountryByCode(String language);
	void addCountry(String country);
	void getAllCountries();
	void getContientByName(String name);
	void deleteCountryByCode(String code);
}
