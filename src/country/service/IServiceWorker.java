package country.service;

import country.model.Country;

public interface IServiceWorker {
	Country get = null;

	void dealWithCountryByCode(String language);

	Country getContryFromData(String countryData);

	void getAllCountries();

	void addCountry(String contryData);

	void removeCountry(String CountryCode);

	void updateCountry(String countryCode, String countryData);
}
