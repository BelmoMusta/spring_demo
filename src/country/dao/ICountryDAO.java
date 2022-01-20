package country.dao;

import country.model.Country;

public interface ICountryDAO {
	Country add(Country country);
	Country getByCode(String code);
	Country deleteByCode(String code);
}
