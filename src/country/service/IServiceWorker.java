package country.service;

public interface IServiceWorker {
	void dealWithCountryByCode(String language);
	void saveCountry(String input);
	void deleteCountry(String code);
	void dealWithContinentByCode(String code);
	void updateCountry(String codeCountry, String info);
	boolean countryExists(String code);
}
