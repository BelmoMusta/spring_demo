package country.dao;

import java.util.List;

import country.model.Country;

public interface CountryDAO {
	Country getByCode(String code); //tapper 2
	Country addCountry(String code, String nom, String devise, String greeting); //tapper 1
	void deleteCountry(String code); //tapper 3
	void updateCountry(String code, String nom, String devise, String greeting); //tapper 4
	List<Country> listAllCountries(String codeContinent); //tapper 5
	List<Country> listAll(); //tapper 6
	
}
