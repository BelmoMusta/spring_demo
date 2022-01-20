package country.service;

public interface IServiceWorker {

	void dealWithCountryByCode(String language);

	void addCountry(String countryinfos);

	void getCountryInfos(String countryCode);
}
