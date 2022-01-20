package country.service;

import country.model.Continent;
import country.model.Country;

public interface IServiceWorker {

	void dealWithCountryByCode(String language);
	void addCountry(String country);
	void getAllCountries();
	Continent getContinentByName(String name);
	void deleteCountryByCode(String code);
	void getContinentCountries(String code);
	void updateCountry(String country);
	void updateCountry(Country country);
	
	
}
