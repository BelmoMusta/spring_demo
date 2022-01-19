package country.service;

public interface IServiceWorker {
	void dealWithCountryByCode(String language);

	void getAllCountries();

	void addCountry(String contryData);

	void removeCountry(String CountryCode);

}
