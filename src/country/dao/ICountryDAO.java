package country.dao;

import country.model.Continent;
import country.model.Country;

import java.util.List;

public interface ICountryDAO {
	Country add(Country country);
	Country getByCode(String code);
	Country deleteByCode(String code);
	List<Country> getByContinent(Continent continent);
}
