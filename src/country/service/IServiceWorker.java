package country.service;

import country.model.Country;

import java.util.List;

public interface IServiceWorker {
	void dealWithCountryByCode(String language);
	void modifierPays(String countryCode, String newFields);
	void ajouterPays(String country);
	void supprimerPays(String code);

    Country getCountryData(String code);





	List<Country> getCountriesByContinent(String continentCode);
}
