package country.service;

import country.model.Continent;
import country.model.Country;

import java.util.Scanner;

public interface IServiceWorker {
	void dealWithCountryByCode(String language);
	void addCountry(String country);
	void getContinentCountries(String code);
	void getAllCountries();
	void deleteCountryByCode(String code);

	Continent getContientByName(String name);
	void updateCountry(String countryCode, Scanner inputFromConsole);

}
