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
	public void InsertNewCountry(Country country,String nameOfContinet) {
		countryDAO.insert(country,nameOfContinet);
	}
	
	@Override
	public void deleteCountryByCode(String code) {
		countryDAO.deleteCountryByCode(code);
		
	}

	@Override
	public void updateCountry(String code, Country country, String nameOfContinet) {
		countryDAO.updateCountry(code, country,nameOfContinet);
		
	}
	
	@Override
	public void ListCountryData(String code) {
		Country country=countryDAO.getByCode(code);
		if(country==null) {
			System.out.println("** Il n'y aucun pays avec ce code **");
		}else{
		System.out.println("          Informations sur le pays   ");
		System.out.println("Nom :"+country.getName());
		System.out.println("Code :"+country.getCode());
		System.out.println("Devise :"+country.getDevise());
		System.out.println("Salutation :"+country.getGreetings());
		System.out.println("Continent :"+country.getContinent().getName());
		}
	}

	

}
