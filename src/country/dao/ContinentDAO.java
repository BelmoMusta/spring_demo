package country.dao;

import country.model.Continent;

public interface ContinentDAO {
    Continent getContientByCode(String code);

}
