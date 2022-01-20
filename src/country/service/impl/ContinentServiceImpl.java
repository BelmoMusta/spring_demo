package country.service.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import country.model.Continent;

@Scope(value = "prototype")
@Service
public class ContinentServiceImpl extends AbstractContinentService {
    private final Continent continent;

    public ContinentServiceImpl(Continent continent) {
        this.continent = continent;
    }

    @Override
    public Continent getContinent() {
        return continent;
    }
}
