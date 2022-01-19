package country.service;

public interface IServiceWorker {
	void dealWithCountryByCode(String language);
	void addCountry(String info);

	void getCountryInfos(String countryCode);

	void deletCountry(String countryCode);

	void updateCountry(String countryCode, String newFields);

	void getCountries();
}