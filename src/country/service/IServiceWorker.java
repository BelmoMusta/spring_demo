package country.service;

import country.model.Country;

public interface IServiceWorker {
	void dealWithCountryByCode(String language);

	void ajouterCountry(Country _country, String _nameContinent);

	void afficheCountry(String input);

	void SuppCountry(String input);

	void ModifyCountry(Country country, String code, String string);

	void selectCountriesByContinent(String _inputCode);

	
}
