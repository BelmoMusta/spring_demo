package country.service.impl;

import country.dao.CountryDAO;
import country.model.Country;
import country.service.ICountryService;
import country.service.IServiceWorker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class ServiceWorkerImpl implements IServiceWorker {
	@Autowired
	private CountryDAO countryDAO;
	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public void getCountryByCode(String language) {
		Country pays = countryDAO.getCountryByCode(language);
		// car c'est prototype
		ICountryService countryService = applicationContext.getBean(ICountryService.class, pays);

		System.out.println("WELCOME : " + countryService.welcome());
		System.out.println("Devise is :" + countryService.devise());
	}

	@Override
	public void getAllCountries() {
		// TODO Auto-generated method stub
		countryDAO.getAllCountries();
	}

	public void addCountry(String countryData) {
		// TODO Auto-generated method stub
		countryDAO.addCountry(getContryFromData(countryData));
	}

	public void updateCountry(String coutryCode, String countryData) {
		// TODO Auto-generated method stub
		countryDAO.updateByCode(coutryCode, getContryFromData(countryData));
	}

	public Country getContryFromData(String countryData) {
		Country country = new Country();
		String[] data = countryData.split(",");
		country.setCode(data[0]);
		country.setName(data[1]);
		country.setContinent(data[2]);
		country.setDevise(data[3]);
		country.setGreetings(data[4]);
		return country;
	}

	public void removeCountry(String CountryCode) {
		// TODO Auto-generated method stub
		countryDAO.removeCountry(CountryCode);
	}

	public void getAllCountriesInContinent(String continentCode) {
		// TODO Auto-generated method stub
		List<Country> countries = countryDAO.getAllCountriesInContinent(continentCode);
		String continentName = countryDAO.getContinentName(continentCode);
		System.out.println("les pays que se trouvent dans le continent " + continentName + " sont:");
		for (Country country : countries) {
			System.out.println("- " + country.getName());
		}
	}
}
