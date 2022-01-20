package country.service;

public interface IServiceWorker {
	void dealWithCountryByCode(String language);
	void addNewCountry(String countryInfo);
	void deleteCountryByCode(String code);
	void ModifCountryByCode(String code);
	void continentCountriesByCode(String code);
}
