package app.service.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import app.entity.Country;

@Scope(value = "prototype") // le service sera utilis� plusieurs fois, selon le pays en entr�e.
							// i.e : � chaque appel, une nouvelle instance sera cr��e
@Service
public class CountryServiceImpl extends AbstractCountryService {
	private final Country country;
	
	public CountryServiceImpl(Country country){
		this.country = country;
	}
	@Override
	public Country getCountry() {
		return country;
	}
}
