package country.service;

import java.util.List;

import country.model.Country;

public interface IServiceWorker {
	void dealWithCountryByCode(String code);
	void addCountry(String infos);
	void deleteCountry(String code);
	void updateCountry(String code, String[] countryNInfos);
	void countries(String continentCode);
	
	boolean checkIfCountryExists(String code);
	boolean checkIfContinentExists(String Ccode);
	List<String> countryElements(String countryCode);
}

