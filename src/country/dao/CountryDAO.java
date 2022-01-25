package country.dao;

import java.util.List;

import country.model.Country;

public interface CountryDAO {


	public boolean enregistrerCountry(Country country);
	public List<Country> listCountry();
	public Country findByCode(String code);
	public boolean  supprimerByCode(String code);
	public boolean  modificationByCode(String code, Country contry);
	public List<Country>findCountryByContinent(String codecontin) ;
	
	
}


