package country.dao;

import country.model.Continent;

public interface ContinentDAO {
    boolean continentExists(String continentCode);
    Continent getByCode(String continentCode);
    Continent getByName(String continentName);
}
