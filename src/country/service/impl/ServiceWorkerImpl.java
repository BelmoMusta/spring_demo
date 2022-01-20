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

import java.util.List;

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
	public void addCountry(String countryinfos) {

		Country country = new Country();
		Continent continent = new Continent();

		country.setCode(countryinfos.split(",")[0]);
		country.setName(countryinfos.split(",")[1]);
		country.setDevise(countryinfos.split(",")[2]);
		country.setGreetings(countryinfos.split(",")[3]);

		String continent_id = countryinfos.split(",")[4];
		continent = continentDAO.getContinentByCode(continent_id);

		country.setContinent(continent);
		countryDAO.saveCountry(country);

	}

	@Override
	public Country getCountryInfos(String countryCode) {

		Country country = countryDAO.getByCode(countryCode);
		System.out.println(country.toString());
		return country;
	}

	@Override
	public void deleteCountry(String countryCode) {
		Country country = countryDAO.getByCode(countryCode);
		countryDAO.deleteCountry(country);
	}

	@Override
	public void updateCountry(String countryCode, String update) {
		Country country = countryDAO.getByCode(countryCode);
		Continent continent;
		String[] fields = update.split(",");

		continent = continentDAO.getContinentByCode(fields[4]);

		country.setCode(fields[0]);
		country.setName(fields[1]);
		country.setDevise(fields[2]);
		country.setGreetings(fields[3]);
		country.setContinent(continent);
		countryDAO.updateCountry(country);
	}

	@Override
	public List<Country> getCountries(String continentcode) {

		for (Country country : countryDAO.getAllCountries(continentcode)) {
			System.out.println(country.toString());
			System.out.println("\n");
		}
		return  countryDAO.getAllCountries(continentcode);
	}
}

