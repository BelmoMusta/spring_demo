package country.service;

public interface IServiceWorker {
	void dealWithCountryByCode(String language);

	void addNewCountry(String country);

	void DeleteCountryByCode(String Code);

	void EditCountryByCode(String code, String ModifyInfos);

}
