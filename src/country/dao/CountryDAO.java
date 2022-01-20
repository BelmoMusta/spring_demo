package country.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import country.model.Continent;
import country.model.Country;
@Transactional
public interface CountryDAO {
	int add(Country country,String nameOfContinet);
	Continent getByName(String name);

	Country getByCode(String code);
    int deleteByCode(String code);
    int updateByCode(Country country,String code,String nameOfContinet);
	Continent getContinentByCode(String code); 
	List<Country> getCountrieByCode(String code);
}
