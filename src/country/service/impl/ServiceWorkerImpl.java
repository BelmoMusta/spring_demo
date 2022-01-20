package country.service.impl;

import country.dao.CountryDAO;
import country.dao.ContinentDAO;
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
	public void addCountry(String countryinfos){

		Country country = new Country();
		Continent continent = new Continent();

		String continent_identifiant = countryinfos.split(",")[4];
		continent = continentDAO.getContinentByCode(continent_identifiant);

		country.setCode(countryinfos.split(",")[0]);
		country.setName(countryinfos.split(",")[1]);
		country.setName(countryinfos.split(",")[2]);
		country.setName(countryinfos.split(",")[3]);
		country.setGreetings(countryinfos.split(",")[4]);
		country.setContinent(continent);
		countryDAO.saveCountry(country);

	}

	@Override
	public void getCountryInfos(String countryCode) {

		Country pays = countryDAO.getByCode(countryCode);
		ICountryService countryService = applicationContext.getBean(ICountryService.class, pays);

		System.out.println("Country's name is : " + countryService.name());
		System.out.println("Country's welcome is : " + countryService.welcome());
		System.out.println("Country's devise is : " + countryService.devise());
		System.out.println("Country's Continent is : " + countryService.continentName());

	}
}
