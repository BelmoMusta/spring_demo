package country.service;

import country.model.Country;

public interface IServiceWorker {
	//Aspect fonctionnel 1
	void dealWithAddCountry(Country country,String nomContinet);
	
	//Aspect fonctionnel 2
	void dealWithCountryByCode(String language);
	
	//Aspect fonctionnel 3
	void dealWithDeleteCountry(String code);
	
	//Aspect fonctionnel 4
	void dealWithUpdateCountry(String code, Country country, String nomContinet);
	
	//Aspect fonctionnel 5
	void dealwWithSelectCountriesOfContinent(String code);
}
