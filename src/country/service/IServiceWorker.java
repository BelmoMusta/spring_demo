package country.service;

import country.model.Country;

public interface IServiceWorker {
	void dealWithCountryByCode(String language);
	void addCountry(String information);
	Country getCountry(String code);
	Country getCountryInfos(String code);
	void deleteCountry(String code);
}
