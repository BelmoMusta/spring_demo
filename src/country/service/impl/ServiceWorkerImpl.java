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


	public void addCountry(String country){
		String[] countryParts = country.split(",");
		String code = countryParts[0];
		String name = countryParts[1];
		String device = countryParts[2];
		String greeting = countryParts[3];
		Country c = new Country();
		c.setCode(code);
		c.setDevise(device);
		c.setName(name);
		c.setGreetings(greeting);
		countryDAO.save(c);
	}

	public void getAllCountries(){
		countryDAO.getALl().forEach(c -> System.out.println(c.toString()));
	}
}
