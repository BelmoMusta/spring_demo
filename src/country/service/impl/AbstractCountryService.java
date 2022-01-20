package country.service.impl;

import country.domain.Country;
import country.service.ICountryService;


public abstract class AbstractCountryService implements ICountryService {
	
	public String welcome() {
		return getCountry().getGreet();
	}
	
	@Override
	public String devise() {
		return getCountry().getDevise();
	}
	
	public abstract Country getCountry();// c'est quoi ton pays ?
}
