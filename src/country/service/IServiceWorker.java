package country.service;

public interface IServiceWorker {

	void dealWithCountryByCode(String language);

	void addCountry(String countryinfos);

	void getCountryInfos(String countryCode);

	void deleteCountry(String countryCode);

	void updateCountry(String countryCode, String update);
}
