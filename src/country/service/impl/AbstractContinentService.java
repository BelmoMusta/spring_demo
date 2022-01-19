package country.service.impl;

import country.model.Continent;
import country.model.Country;
import country.service.IContinentService;
import country.service.ICountryService;

import java.util.List;


public abstract class AbstractContinentService implements IContinentService {

	@Override
	public List<Country> countries() { return (List<Country>) getContinent().getCountries(); }
	public abstract Continent getContinent();// what is your continent?
}
