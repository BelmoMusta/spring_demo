package country.dao;

import country.model.Continent;

public interface ContinentDAO {
    Continent getContientByName(String name);
}
