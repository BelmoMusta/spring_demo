package country.service;

import country.model.Country;

public interface IServiceWorker {

	void dealWithCountryByCode(String language);
	void addNewCountry(String data);
	void getCountryData(String Code);
	void deletCountry(String countryCode);
	void updateCountry(String Code, String update);
	void getCountries(String code);


}
