package country.service;

public interface IServiceWorker {
	void getCountry(String language);
	void addCountry(String countryInfos);
	void deleteCountry(String countryCode);
	void updateCountry(String countryCode, String newCountryInfos);
	void getCountriesOfContinent(String ContinentCode);
}
