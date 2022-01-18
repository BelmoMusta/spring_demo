package country.service;

public interface IServiceWorker {
	void dealWithCountryByCode(String language);

	void getAllCountriesInContinent(String continentCode);
}
