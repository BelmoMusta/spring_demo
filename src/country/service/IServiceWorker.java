package country.service;

import country.model.Country;

public interface IServiceWorker {
	void dealWithCountryByCode(String language);
	void InsertNewCountry(Country country,String nameOfContinet);
	void ListCountryData(String code);
	void deleteCountryByCode(String code);
	void updateCountry(String code, Country country, String nameOfContinet);
	void listCountriesByContinent(String continentCode);
	
}
