package country.service.impl;

import country.model.Continent;
import country.model.Country;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Scope(value = "prototype")
@Service
public class ContinentServiceImpl extends AbstractContinentService {
	private Continent continent;
	public ContinentServiceImpl(Continent continent){
		this.continent = continent;
	}
	@Override
	public Continent getContinent() {
		return continent;
	}
}
