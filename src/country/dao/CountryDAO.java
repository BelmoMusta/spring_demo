package country.dao;


import java.util.List;

import country.model.Country;

public interface CountryDAO {
	public Country getByCode(String code);
	public void saveCountry(Country country);
	public List<Country> findCountrys();
	public void deleteCountryByCode(String code);
	public void updateCountry(Country country);
}
