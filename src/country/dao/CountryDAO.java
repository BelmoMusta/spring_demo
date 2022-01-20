package country.dao;

import java.util.List;

import country.model.Continent;
import country.model.Country;

public interface CountryDAO {

	Country getCountyByCode(String code);

	public Integer AddNewCountry(Country country);

	public Integer DeleteCountry(String Code);

	public Integer EditCountryInfos(String code, Country country);

	public List<Country> CountriesOfContinent(String codeContinent);

	public Continent GetContinent(String CodeContinent);

}
