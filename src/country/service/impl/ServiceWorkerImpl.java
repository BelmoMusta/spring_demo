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
	public void addCountry(String countryInfos) {
		Country country = stringToCountry(countryInfos);
		if(countryDAO.addCountry(country)) {
			System.out.println("Country added");
		}else {
		    System.err.println("Some problem happened");
		}
		
	}


	@Override
	public void deleteCountry(String countryCode) {
		boolean isDeleted =  countryDAO.deleteCountry(countryCode);
		if(isDeleted) {
			System.out.println("Country deleted");
		}else {
			System.err.println("Some problem happened");
		}
		
	}

	@Override
	public void updateCountry(String countryCode, String newCountryInfos) {
		Country country = stringToCountry(newCountryInfos);
		boolean isUpdated = countryDAO.updateCountry(countryCode, country);
		if(isUpdated) {
			System.out.println("Country Updated");
		}else {
			System.out.println("Some problem happened");
		}
	}
	
	private Country stringToCountry(String countryInfos) {
		String[] arrCountryInfos = new String[4];
		String[] availableCountryInfos = countryInfos.split(",");
		for(int i=0;i<availableCountryInfos.length;i++) {
			arrCountryInfos[i] = availableCountryInfos[i];
		}
		Country country = new Country();
		country.setCode(arrCountryInfos[0]);
		country.setName(arrCountryInfos[1]);
		country.setDevise(arrCountryInfos[2]);
		country.setGreetings(arrCountryInfos[3]);
		return country;
	}
	
}
