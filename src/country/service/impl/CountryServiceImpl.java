package country.service.impl;

import country.model.Country;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

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

	@Override
	public String name() {

		return getCountry().getName();
	}

	@Override
	public String continent() {

		return getCountry().getContinent().getName();
	}
}
