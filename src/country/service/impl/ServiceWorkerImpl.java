package country.service.impl;

import country.dao.CountryDAO;
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
	public void addNewCountry(String countryInfo) {
		String[] countryInformations = countryInfo.split(",", 5);
		countryDAO.addNewCountry(countryInformations );
		System.out.println(" Le pays est ajouté");
	}

	@Override
	public void deleteCountryByCode(String code) {
		countryDAO.deleteCountry(code);
		System.out.println("Le pays est supprimé ");
	}

	@Override
	public void ModifCountryByCode(String code) {
		countryDAO.modifCountry(code);
		System.out.println("Le pays modifié ");
	}

	@Override
	public void continentCountriesByCode(String code) {
		System.out.print(countryDAO.continentCountries(code));
	}
	
}
