package country.dao;

import java.util.List;

import country.model.Continent;
import country.model.Country;

public interface CountryDAO {
	Country getByCode(String code);

	int ajouter(Country _country, String _nameOfContinet);

	Continent getByName(String name);
	
	int SuppByCode(String code);

	int updateByCode(Country country, String code, String nameOfContinet);
	List<Country> getCountrieByCode(String code);
	Continent getContinentByCode(String code); 


}
