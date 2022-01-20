package country.service;

import java.util.List;

import country.model.Country;

public interface IServiceWorker {
	void dealWithCountryByCode(String language);
	void SaveCountryByInformations(String Informations);
	void dealWithDeleteCountry(String code);
	void UpdateCountryByInformations(String lang, String Informations);
	void CountriesOfContinent(String codeContinent);
}
