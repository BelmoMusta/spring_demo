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
		
		System.out.println("Name : " + pays.getName());
		System.out.println("Devise : " + pays.getDevise());
		System.out.println("Greetings : " + pays.getGreetings());
		System.out.println("Code : " + pays.getCode());

	}

	@Override
	public void SaveCountry(String infos) {
		String[] countryInfos = infos.split(",");
		Country country = countryDAO.saveCountry(new Country(countryInfos[0],countryInfos[1],countryInfos[2],countryInfos[3],countryInfos[4]));
		ICountryService countryService = applicationContext.getBean(ICountryService.class, country);
		System.out.println("Pays enregestré avec succes");
	}
}
