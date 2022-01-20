package country.service;

public interface IServiceWorker {
	void dealWithCountryByCode(String code);
	void addCountry(String infos);
	void deleteCountry(String code);
	void updateCountry(String code);
	void countries(String continentCode);
	
	boolean checkIfCountryExists(String code);
	boolean checkIfContinentExists(String Ccode);
}

