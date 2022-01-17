package country.service;

public interface IServiceWorker {
	void dealWithCountryByCode(String language);
	void addNewCountry(String info);
	void getCountryInformations(String countryCode);
	void deleteCountry(String countryCode);
	void updateCountry(String countryCode, String newData);
	void getCountries();
}