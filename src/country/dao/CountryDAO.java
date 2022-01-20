package country.dao;

import country.model.Continent;
import country.model.Country;

public interface CountryDAO {
	Country getByCode(String code);

	int ajouter(Country _country, String _nameOfContinet);

	Continent getByName(String name);
	
	int SuppByCode(String code);
}
