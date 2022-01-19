package country.service;

import country.model.Country;

public interface IServiceWorker {
	void getCountry(String language);
	void addCountry(String countryInfos, String continentCode);
	void deleteCountry(String countryCode);
	void updateCountry(String countryCode, String newCountryInfos);
	void getCountriesOfContinent(String ContinentCode);
	void initData();
}
