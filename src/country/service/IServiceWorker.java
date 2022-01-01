package country.service;

import country.model.Country;

public interface IServiceWorker {
	void dealWithCountryByCode(String language);
	void saveCountry(Country country);
}
