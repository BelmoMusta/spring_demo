package country.service;

public interface IServiceWorker {
	void dealWithCountryByCode(String code);

	void addCountry(String countryCode);

	void deleteCountry(String countryCode);

	void updateCountry(String countryCode);

	void dealWithContinentByCode(String code);
}
