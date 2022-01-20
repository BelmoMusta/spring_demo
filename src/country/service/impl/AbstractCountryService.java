package country.service.impl;

import country.model.Country;
import country.service.ICountryService;

public abstract class AbstractCountryService implements ICountryService {

	public String welcome() {
		return getCountry().getGreetings();
	}

	public String name() {
		return getCountry().getName();
	}

	public String continent() {
		return getCountry().getContinent();
	}

	public String devise() {
		return getCountry().getDevise();
	}

	public abstract Country getCountry();// c'est quoi ton pays ?
}
