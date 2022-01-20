package country.service;

import country.model.Country;
public interface IServiceWorker {
	
	void AjouterCountry(Country country,String continent);
	void dealWithCountryByCode(String code);
	void supprimerCountry(String code);
	void modifierCountry(Country country,String code,String continent);
	void ListerCountriesOfContinent(String code);
	
}