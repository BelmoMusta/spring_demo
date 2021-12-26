package country.service;

public interface IServiceWorker {
	void dealWithCountryByCode(String language);
	void addCountry(String countryInfos);
	void deleteCountry(String countryCode);
	void updateCountry(String countryCode, String newCountryInfos);
}
