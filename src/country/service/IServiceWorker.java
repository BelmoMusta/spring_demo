package country.service;

import country.model.Country;

import java.util.List;

public interface IServiceWorker {
	void dealWithCountryByCode(String language);
	void addCountry(String information);
	Country getCountryByCode(String code);
	Country getInfos(String code);
	void deleteCountry(String code);
	void updateCountry(String code, String information);
	List<Country> getCountries(String code);

}
