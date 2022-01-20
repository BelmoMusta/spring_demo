package country.service;
import country.model.Country;

public interface IServiceWorker {
	void displayCountry();
	void addCountry(String country);
	public void displayByCode(String code);
	public void deletByCode(String code);
	void updateByCode(String code, String nvCountry);
	
	Country getCountryByCode(String code);
 
	
}
