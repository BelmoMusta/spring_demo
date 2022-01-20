package country.service.impl;

import country.model.Country;
import country.service.ICountryService;


public abstract  class AbstractCountryService implements ICountryService {
	
	public String welcome() {
		return getCountry().getGreetings();
	}
	
	public abstract Country getCountry();// c'est quoi ton pays ?

	public String devise() {
		// TODO Auto-generated method stub
		return getCountry().getDevise();
	}
}
