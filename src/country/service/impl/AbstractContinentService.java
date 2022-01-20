package country.service.impl;

import java.util.List;

import country.model.Continent;
import country.model.Country;
import country.service.IContinentService;

public abstract class AbstractContinentService implements IContinentService {
    @Override
    public List<Country> countries() {
        return (List<Country>) getContinent().getCountries();
    }

    public abstract Continent getContinent();
}
