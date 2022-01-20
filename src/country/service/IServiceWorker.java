package country.service;

import java.sql.SQLException;

public interface IServiceWorker {
	void dealWithCountryByCode(String language); //tapper 2
	void dealWithAddCountry(String code, String nom, String devise, String greeting); //tapper 1
	void dealWithDeleteCountry(String code); //tapper 3
	void dealWithUpdateCountry(String code, String nom, String devise, String greeting); //tapper 4
	void dealWithListAllCountries(String codeContinent); //tapper 5
	void dealWithListAll() throws SQLException; //tapper 6

}
