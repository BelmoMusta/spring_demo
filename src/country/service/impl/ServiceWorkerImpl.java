package country.service.impl;

import country.dao.ContinentDAO;
import country.dao.CountryDAO;
import country.model.Continent;
import country.model.Country;
import country.service.ICountryService;
import country.service.IServiceWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class ServiceWorkerImpl implements IServiceWorker {
	@Autowired
	private CountryDAO countryDAO;
	@Autowired
	private ContinentDAO continentDAO;
	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public void dealWithCountryByCode(String language) {
		Country pays = countryDAO.getByCode(language);
		// car c'est prototype
		ICountryService countryService = applicationContext.getBean(ICountryService.class, pays);

		System.out.println("WELCOME : " + countryService.welcome());
		System.out.println("Devise is :" + countryService.devise());
	}

	@Override
	public void addCountry(String info) {
		Country country = new Country();
		Continent continent = new Continent();
		
		String continentId = info.split(",")[4];
		continent=continentDAO.getContientByCode(continentId);

		country.setCode(info.split(",")[0]);
		country.setName(info.split(",")[1]);
		country.setDevise(info.split(",")[2]);
		country.setGreetings(info.split(",")[3]);
		country.setContinent(continent);
		countryDAO.saveCountry(country);
	}

	@Override
	public void getCountryInfos(String countryCode) {
		Country pays = countryDAO.getByCode(countryCode);
		// car c'est prototype
		ICountryService countryService = applicationContext.getBean(ICountryService.class, pays);

		System.out.println("Name is :" + countryService.name());
		System.out.println("WELCOME : " + countryService.welcome());
		System.out.println("Devise is :" + countryService.devise());
		System.out.println("Continent is :" + countryService.continentName());
	}

	@Override
	public void deletCountry(String countryCode) {
		Country country = countryDAO.getByCode(countryCode);
		countryDAO.deleteCountry(country);


	}

	@Override
	public void updateCountry(String countryCode, String newFields) {
		Country country = countryDAO.getByCode(countryCode);
		Continent continent;
		String[] fields = newFields.split(",");

		continent=continentDAO.getContientByCode(fields[4]);

		country.setCode(fields[0]);
		country.setName(fields[1]);
		country.setDevise(fields[2]);
		country.setGreetings(fields[3]);
		country.setContinent(continent);
		countryDAO.updateCountry(country);
	}

	@Override
	public void getCountries() {

		for( Country country : countryDAO.getAllCountries()){
			System.out.println(country.toString());
			System.out.println("\n");

		}
	}
}