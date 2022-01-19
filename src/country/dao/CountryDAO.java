package country.dao;

import country.model.Country;

import java.util.List;

public interface CountryDAO {
	Country getByCode(String code);
	void modifierUnpays(Country country);

	void enregistrerUnpays(Country country);

	void supprimerUnpays(Country country);



	List<Country> getCountriesByContinent(String continentCode);
}
