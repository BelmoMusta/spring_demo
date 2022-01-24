package country.dao;

import country.model.Continent;


public interface ContinentDAO {
    Continent getByCode(String code);
    Continent getByID(String id);
}
