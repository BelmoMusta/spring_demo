package country.dao;

import java.util.List;

import country.model.Continent;
import country.model.Country;

public interface CountryDAO {
	Country getByCode(String code);
	int insert(Country country,String nameOfContinet);
	Continent getContinentByName(String name);
	public int deleteCountryByCode(String code);
	int updateCountry(String code, Country country, String nameOfContinet);
	List<Country> listCountriesByContinent(String continentCode);
	Continent getContinentByCode(String code);
}
