package country.service;


import country.model.Country;

import java.util.List;

public interface IServiceWorker {
	void dealWithCountryByCode(String language);
	void SaveCountry(String country);
	void DeleteCountry(String code);
	void UpdateCountry(String code, String country);
	void GetCountriesByContinentCode(String continentCode);
	Country GetCountryByCode(String CountryCode);
}
