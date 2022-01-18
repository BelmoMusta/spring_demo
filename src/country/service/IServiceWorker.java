package country.service;

public interface IServiceWorker {
	void dealWithCountryByCode(String language);

	void updateCountry(String countryCode, String countryData);
}
