package country.service.impl;

import country.dao.ContiDAO;
import country.dao.CountryDAO;
import country.model.Continent;
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
	private ContiDAO contiDAO;
	@Autowired
	private ApplicationContext applicationContext;
	
	@Override
	public void dealWithCountryByCode(String language) {
		Country pays = countryDAO.getByCode(language);
		// car c'est prototype
		ICountryService countryService = applicationContext.getBean(ICountryService.class, pays);
		
		System.out.println("WELCOME : " + countryService.welcome());
		System.out.println("Devise is :" + countryService.devise());
		System.out.println("Continent is :" + countryService.continent());

	}
	
	@Override
	public void addCountry(String country) {
		String[] elt = country.split(",");
		String code = elt[0];
		String name = elt[1];
		String device = elt[2];
		String greeting = elt[3];

		Country c = new Country();
		c.setCode(code);
		c.setDevise(device);
		c.setName(name);
		c.setGreetings(greeting);
		countryDAO.addCountry(c);
	}
	
	@Override
	public void modifyCountryByCode(String code, Country c) {
		// TODO Auto-generated method stub
		countryDAO.modifyCountry(code,c);
	}
	
	@Override
	public void deleteCountryByCode(String code) {
		countryDAO.deleteCountry(code);
	}
	
	@Override
	public void getContinentCountriesByCode(String code) {
		
			contiDAO.getContinentByCode(code).getCountries().forEach(System.out::println);
		
			
	}

	@Override
	public void getAllCountries() {
		// TODO Auto-generated method stub
		
	}
}
