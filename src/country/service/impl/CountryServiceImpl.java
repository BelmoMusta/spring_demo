package country.service.impl;

import country.model.Country;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Scope(value = "prototype") 
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
