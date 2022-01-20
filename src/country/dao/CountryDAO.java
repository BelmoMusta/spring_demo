package country.dao;
import country.model.Country;
import java.util.List;

public interface CountryDAO {
	Country getByCode(String code);
	void addNewCountry(String [] countryInformations);
	void deleteCountry(String code);
	void modifCountry(String code,String newInfos);
	List <String> continentCountries(String code);  
}
