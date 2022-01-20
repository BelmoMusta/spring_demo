package country.dao;

import java.util.List;

import country.model.Continent;
import country.model.Country;

public interface CountryDAO {
	Country getByCode(String code);
	void addCountry(Country country,String nameOfContinet);
	void deleteCountry(String code);
	boolean exist(String code);
}
