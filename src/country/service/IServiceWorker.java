package country.service;

import country.model.Country;

public interface IServiceWorker {
	Country dealWithCountryByCode(String language);

	void addNewCountry(String country);

	void DeleteCountryByCode(String Code);

	Integer EditCountryByCode(String code, String ModifyInfos);

	void CountriesOfSameContinent(String codeContinent);

}
