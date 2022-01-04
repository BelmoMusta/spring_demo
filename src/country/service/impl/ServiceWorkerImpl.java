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
	public void addCountry(String informations) {
		Country country = new Country();
		String [] infos = informations.split(",");
		country.setCode(infos[0]);
		country.setName(infos[1]);
		country.setDevise(infos[2]);
		country.setDevise(infos[3]);
		countryDAO.addCountry(country);
	}


	@Override
	public void deleteCountry(String code) {
		countryDAO.deleteCountry(code);
	}
	
	//add Country
}
