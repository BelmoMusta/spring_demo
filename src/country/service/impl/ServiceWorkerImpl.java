package country.service.impl;

import country.dao.ContinentDAO;
import country.dao.CountryDAO;
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
	public void addNewCountry(String info) {
		Country country = new Country();

		country.setCode(info.split(",")[0]);
		country.setName(info.split(",")[1]);
		country.setDevise(info.split(",")[2]);
		country.setGreetings(info.split(",")[3]);
		String continentId = info.split(",")[4];

		country.setContinent(continentDAO.getContinentByCode(continentId));
		countryDAO.setNewCountry(country);
	}

	@Override
	public Country getCountryInformations(String countryCode) {
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
	public void updateCountry(String countryCode, String newData) {
		Country country = countryDAO.getByCode(countryCode);
		String[] data = newData.split(",");

		country.setCode(data[0]);
		country.setName(data[1]);
		country.setDevise(data[2]);
		country.setGreetings(data[3]);
		country.setContinent(continentDAO.getContinentByCode(data[4]));

		countryDAO.updateCountry(country);
	}

	@Override
	public List<Country> getCountriesByContinent(String continentCode) {

		for( Country country : countryDAO.getCountriesByContinent(continentCode)){
			System.out.println(country.toString());
		}
		return countryDAO.getCountriesByContinent(continentCode);
	}
}
