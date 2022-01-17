package country.service.impl;

import country.dao.ContinentDAO;
import country.dao.CountryDAO;
import country.model.Country;
import country.model.Continent;
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
		continent=continentDAO.getContinentByCode(continentId);
		country.setCode(info.split(",")[0]);
		country.setName(info.split(",")[1]);
		country.setDevise(info.split(",")[2]);
		country.setGreetings(info.split(",")[3]);
		country.setContinent(continent);
		countryDAO.saveCountry(country);
	}

	@Override
	public void getInformations(String countryCode) {
		Country pays = countryDAO.getByCode(countryCode);
		ICountryService countryService = applicationContext.getBean(ICountryService.class, pays);

		System.out.println("Le nom du pays est :" + countryService.name());
		System.out.println("Son salutation est : " + countryService.welcome());
		System.out.println("Sa devise est :" + countryService.devise());
		System.out.println("Son continent est :" + countryService.continentName());
	}

	@Override
	public void deleteCountry(String countryCode) {
		Country country = countryDAO.getByCode(countryCode);
		countryDAO.deleteCountry(country);
	}

	@Override
	public void updateCountry(String countryCode, String fields) {
		Country country = countryDAO.getByCode(countryCode);
		Continent continent;
		String[] dataFields = fields.split(",");
		continent=continentDAO.getContinentByCode(dataFields[4]);
		country.setCode(dataFields[0]);
		country.setName(dataFields[1]);
		country.setDevise(dataFields[2]);
		country.setGreetings(dataFields[3]);
		country.setContinent(continent);
		countryDAO.updateCountry(country);
	}

	@Override
	public void getCountries() {
		for( Country country : countryDAO.getAllCountries()){
			System.out.println(country.toString());
		}
	}
}
