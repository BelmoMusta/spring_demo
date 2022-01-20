package country.service;

import java.util.List;

import country.model.Country;

public interface IServiceWorker {
	
	void AddCountry(String country);
	public void listCountry();
	public void findByCode(String code);
	public void deleteByCode(String code);
	public void updateByCode(String code, String country);
	//List<Country> getCountriesByContinent(String continentCode);
}
