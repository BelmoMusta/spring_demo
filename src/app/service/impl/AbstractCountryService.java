package app.service.impl;

import app.entity.Country;
import app.service.ICountryService;


public abstract class AbstractCountryService implements ICountryService {
	
	public String welcome() {
		return getCountry().getGreetings();
	}
	
	@Override
	public String devise() {
		return getCountry().getDevise();
	}
	
	public abstract Country getCountry();// c'est quoi ton pays ?
}
