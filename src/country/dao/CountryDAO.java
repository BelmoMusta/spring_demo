package country.dao;

import country.model.Continent;
import country.model.Country;

public interface CountryDAO {
	void add(Country country,String nameOfContinet);
	Continent getByName(String name);
	Country getByCode(String code);
}
