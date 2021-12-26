package country.dao;

import country.model.Country;

public interface CountryDAO {
	Country getByCode(String code);
	boolean addCountry(Country country);
	boolean deleteCountry(String countryCode);
	boolean updateCountry(String countryCode, Country newCountryInfos);
}
