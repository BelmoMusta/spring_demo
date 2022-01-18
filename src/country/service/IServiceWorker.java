package country.service;

public interface IServiceWorker {
	void dealWithCountryByCode(String language);
	void saveCountry(String input);
	void deleteCountry(String code);
}
