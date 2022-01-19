package country.service;

import country.model.Country;

public interface IServiceWorker {
	void dealWithCountryByCode(String language);
	void addCountry(String information);
	Country getCountryByCode(String code);
	Country getInfos(String code);

}
