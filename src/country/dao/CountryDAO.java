package country.dao;

import java.util.List;

import country.model.Country;

public interface CountryDAO {
	Country getByCode(String code);

	List<Country> getAllCountries();

	int addCountry(Country contryFromData);

	int removeCountry(String countryCode);

}
