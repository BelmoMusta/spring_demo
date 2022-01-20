package country.repository;

import java.util.List;

import country.domain.Country;

public interface ICountryDao {
	Country getByCode(String code);
	Country saveCountry(Country country);
	void deleteCountry(Country country);
	Country updateCountry(Country c, String code);
	List<Country> continentCountries(String code);
}
