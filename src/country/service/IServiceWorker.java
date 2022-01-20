package country.service;

import country.model.Country;

public interface IServiceWorker {
	void InsertCountry(Country country,String nameOfContinet);
	void selectCountry(String code);
	void deleteCountry(String code);
	void updateCountry(Country country,String code,String nameOfContinet);
	void selectCountriesOfContinent(String code);
	boolean isExist(String code);
}
