package country.dao;

import java.util.List;

import country.model.Country;

public interface CountryDAO {
	Country getByCode(String code);
	void addElement(String[] countryInfos);
	void deleteElement(String code);
	void updateElement(String code, String[] countryNInfos);
	List<String> getContinentCountries(String continentCode);
	
	boolean checkExistance(String code);
	boolean checkContinentExistance(String Ccode);
	List<String> countryElements(String countryCode);
}
