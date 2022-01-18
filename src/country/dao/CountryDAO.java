package country.dao;

import country.model.Country;

import java.util.List;

public interface CountryDAO {
	Country getByCode(String code);
	void save(Country country);
	void deleteByCode(String Code);
}
