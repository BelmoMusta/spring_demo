package country.dao;

import country.model.Continent;

public interface ContinentDAO {
	Continent getByCode(String code);
	Continent getByName(String name);
	boolean exist(String code);

}
