package country.service;


public interface IServiceWorker {
	void dealWithCountryByCode(String language);
	void SaveCountry(String country);
	void DeleteCountry(String code);
	void UpdateCountry(String code, String country);
}
