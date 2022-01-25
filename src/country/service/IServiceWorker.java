package country.service;

import java.util.List;

import country.model.Country;

public interface IServiceWorker {
	List<Country>listCountry();
	void InsertCountry(String country);
	Country findByCode(String code);
    void deleteByCode(String code);
	void updateByCode(String code,Country c);
	void findByCodeContinent(String continentcode);
}
