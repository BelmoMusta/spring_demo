package country.dao;

import java.util.List;

import country.model.Country;

public interface CountryDAO {

	Country getByCode(String code);

	public Integer AddNewCountry(Country country);

	public Integer DeleteCountry(String Code);

	public Integer EditInfos(String code, Country country);

	public List<Country> CountriesOfContinent(String codeContinent);

}
