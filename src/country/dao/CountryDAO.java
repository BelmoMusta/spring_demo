package country.dao;

import java.util.List;

import country.model.Continent;
import country.model.Country;

public interface CountryDAO {
	
	Continent getByName(String name);
	Country getByCode(String code);
	Continent getContinentByCode(String code);
	void saveCountry(Country c);
	List<Country> getALL();
	void deleteCountry(String code);
	boolean exists(String code);
	Country update(Country country);

}
