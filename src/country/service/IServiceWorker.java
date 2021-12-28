package country.service;

public interface IServiceWorker {
	void dealWithCountryByCode(String language);
	void dealWithSaveCountry(String infos);
	void dealWithDeleteCountry(String language);
	void dealWithUpdateCountry(String infos, String language);
}
