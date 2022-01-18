package country.service;

import country.model.Country;

public interface IServiceWorker {


	
	public void listCountry();
	public void AjouterCountry(String country);
	public void findByCode(String code);
	public void supprimerByCode(String code);
	public void modificationByCode(String code, String country);
	

}
