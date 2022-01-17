package country.service;

public interface IServiceWorker {
	void dealWithCountryByCode(String language);
	void addCountry(String info);
	void getInformations(String countryCode);
	void deleteCountry(String countryCode);
	void updateCountry(String countryCode, String newFields);
	void getCountries();
}
