package country.service;

import country.model.Country;

public interface IServiceWorker {
	Country get = null;

	void getCountryByCode(String language);

	Country getContryFromData(String countryData);

	void getAllCountries();

	void addCountry(String contryData);

	void removeCountry(String CountryCode);

	void updateCountry(String countryCode, String countryData);

	void getAllCountriesInContinent(String continentCode);
}
