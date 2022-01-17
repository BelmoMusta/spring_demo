package country.service;

public interface IServiceDetail {
	void afficheCountry(String language);
	void createCountry(String infosContry);
	void deleteCountry(String language);
	void updateCountry(String newInformations);
	void loadContinents(String codeOfContinent);
}
