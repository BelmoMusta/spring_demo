package country.dao;

import java.util.List;

import country.model.Continent;
import country.model.Country;

public interface ContinentDAO {
	Continent getContinentByCode(String code);
	Continent getContientByName(String name);
	List<Country> getCountriesByContinentCode(String code);
    boolean exist(String code);
}
