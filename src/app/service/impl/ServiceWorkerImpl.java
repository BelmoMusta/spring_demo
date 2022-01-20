package app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import app.dao.CountryDAO;
import app.entity.Country;
import app.service.ICountryService;
import app.service.IServiceWorker;

@Service
public class ServiceWorkerImpl implements IServiceWorker {
	@Autowired
	private CountryDAO countryDAO;
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
	public Country addCountry(Country country) {		
		return countryDAO.addCountry(country);
	}

	@Override
	public Country getCountry(String code) {
		return countryDAO.getCountry(code);
	}

	@Override
	public List<Country> getCountries() {
		return countryDAO.getCountries();
	}

	@Override
	public void deleteCountry(String code) {
		countryDAO.deleteCountry(code);
		
	}

	@Override
	public Country updateCountry(Country country) {
		return countryDAO.updateCountry(country);
	}

	@Override
	public List<Country> getCountriesByContinent(String continent) {	
		return countryDAO.getCountriesByContinent(continent);
	}
}
