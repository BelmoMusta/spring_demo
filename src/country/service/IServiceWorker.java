package country.service;

import java.util.List;

import country.model.Country;

public interface IServiceWorker {
	void dealWithCountryByCode(String language);
	public Country getByCode(String code);
	public void saveCountry(Country country);
	public List<Country> findCountrys();
	public void deleteCountryByCode(String code);
	public void updateCountry(Country country);
}
