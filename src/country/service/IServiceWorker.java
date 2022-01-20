package country.service;

import country.model.Country;

public interface IServiceWorker {
	//Aspect fonctionnel 1
	void dealWithAddCountry(Country country,String nameOfContinet);
	
	//Aspect fonctionnel 2
	void dealWithCountryByCode(String language);
	
	//Aspect fonctionnel 3
	void dealWithDeleteCountry(String code);
	
	//Aspect fonctionnel 5
	void selectCountriesOfContinent(String code);
}
