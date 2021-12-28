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
	public void dealWithSaveCountry(String infos) {
		String[] strs = infos.split(",");
		Country country = countryDAO.saveCountry(new Country(strs[0],strs[1],strs[2],strs[3]));
		ICountryService countryService = applicationContext.getBean(ICountryService.class, country);
		System.out.println("WELCOME : " + countryService.welcome());
		System.out.println("Devise is :" + countryService.devise());
	}

	@Override
	public void dealWithDeleteCountry(String language) {
		countryDAO.deleteCountry(language);		
		System.out.println("the country "+language+" is deleted!");
	}

	@Override
	public void dealWithUpdateCountry(String infos, String language) {
		String[] splitInfos = infos.split(",");
		Country country2 = countryDAO.updateCountry(new Country(splitInfos[0],splitInfos[1],splitInfos[2],splitInfos[3]), language);
		ICountryService countryService = applicationContext.getBean(ICountryService.class, country2);
		System.out.println("WELCOME : " + countryService.welcome());
		System.out.println("Devise is :" + countryService.devise());
	}
	
}
