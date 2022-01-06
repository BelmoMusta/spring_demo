package app.service;

import java.util.List;

import app.entity.Country;

public interface IServiceWorker {
	void dealWithCountryByCode(String language);
	Country addCountry(Country country);
	Country getCountry(String code);
	List<Country> getCountries();
	void deleteCountry(String code);
	Country updateCountry(Country country , String code);
}
