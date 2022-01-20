package country.dao;

import country.model.Continent;

public interface ContinentDAO {
    Continent getContinentByCode(String code);
}
