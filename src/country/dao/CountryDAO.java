package country.dao;


import java.util.List;

import country.model.Continent;
import country.model.Country;


public interface CountryDAO {
	
	Country getByCode(String code);

	int AjouterCountry(Country country,String continent);
	
	List<Country> getCountrieByCode(String code);
	
	int supprimerByCode(String code);
	
	
	int modifierByCode(Country country,String code,String continent);
	
	Continent getContinentByCode(String code);
	
	Continent getByName(String name);

	
	
	
	
	
	
	
	
}