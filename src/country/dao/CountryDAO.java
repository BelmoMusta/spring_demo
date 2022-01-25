package country.dao;

import java.util.List;
import country.model.Country;

public interface CountryDAO {
   boolean saveCountry(Country country);
   List<Country> listCountry();
   Country findByCode(String code);
   boolean deleteByCode(String code);
   boolean updateByCode(String code,Country c);
   List<Country> getCountriesofContinent(String continentcode);
}