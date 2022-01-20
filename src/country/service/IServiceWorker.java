package country.service;

import java.util.List;

import country.model.Country;

public interface IServiceWorker {
	void dealWithCountryByCode(String language);
	void addNewCountry(String countryInfo);
	void deleteCountryByCode(String code);
	void ModifCountryByCode(String code,String newInfo);
	Country getCountryInformations(String countryCode);
	List<String> continentCountriesByCode(String code);
}
