package country.dao;

import country.model.Country;

public interface CountryDAO {
	Country getByCode(String code);

	void delete(Country country);

	void save(Country country);

	public void update(Country country);
}
