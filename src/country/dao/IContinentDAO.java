package country.dao;

import country.model.Continent;

import java.util.List;

public interface IContinentDAO {
    Continent add(Continent continent);
    Continent getByName(String name);
    List<Continent> getAll();
}
