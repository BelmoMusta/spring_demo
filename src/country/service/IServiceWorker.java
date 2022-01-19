package country.service;

import java.util.List;

import country.model.Country;

public interface IServiceWorker {


	
	public void listCountry();
	public void AjouterCountry(String country);
	public void findByCode(String code);
	public void supprimerByCode(String code);
	public void modificationByCode(String code, String country);
	public List<Country> findCountryByContinent(String codecontin);
	

}
