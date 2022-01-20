package country.dao;

import country.model.Continent;

public interface ContinentDAO {
    Continent getContientByName(String name);
    boolean exists(String code);
    Continent getContientByCode(String code);
    boolean existsByName(String name);
}
