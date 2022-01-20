package country.service.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import country.domain.Country;

@Scope(value = "prototype") /*à chaque appel, une nouvelle instance sera créée*/
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
