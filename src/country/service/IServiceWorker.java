package country.service;

public interface IServiceWorker {
	void dealWithCountryByCode(String language);

	void removeCountry(String CountryCode);

}
