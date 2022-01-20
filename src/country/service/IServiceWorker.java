package country.service;

public interface IServiceWorker {
	void dealWithCountryByCode(String language);
	void AddCountry();
	void DeleteCountry();
	void GetList();
	void UpdateCountry();
	void Getcontinent();
	void GetCountriesByContinent();
}
