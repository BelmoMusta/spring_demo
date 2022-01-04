package continent.dao;

import java.util.List;

import javax.transaction.Transactional;

import continent.model.Continent;

@Transactional
public interface ContinentDAO {
	Continent getByCode(String code);
	Continent addContinent(Continent continent);
	Continent getContinent(String code);
	List<Continent> getContinents();
	void deleteContinent(String code);
	Continent updateContinent(Continent continent , String code);
}
