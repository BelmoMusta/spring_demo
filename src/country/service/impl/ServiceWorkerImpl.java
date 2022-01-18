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
	public void dealWithCountryByCode(String language) {
		Country pays = countryDAO.getByCode(language);
		// car c'est prototype
		ICountryService countryService = applicationContext.getBean(ICountryService.class, pays);

		System.out.println("WELCOME : " + countryService.welcome());
		System.out.println("Devise is :" + countryService.devise());
	}

	@Override
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
