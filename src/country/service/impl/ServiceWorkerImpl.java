package country.service.impl;

import country.dao.CountryDAO;
import country.model.Country;
import country.service.ICountryService;
import country.service.IServiceWorker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ServiceWorkerImpl implements IServiceWorker {
	@Autowired
	private CountryDAO countryDAO;
	@Autowired
	private ApplicationContext applicationContext;
	
	@Override
	public void getCountry(String language) {
		Country pays = countryDAO.getByCode(language);
		if(pays == null) {System.out.println("Ce pays n'existe pas");return;}
		ICountryService countryService = applicationContext.getBean(ICountryService.class, pays);
		System.out.println("NAME is   : " + countryService.name());
		System.out.println("WELCOME   : " + countryService.welcome());
		System.out.println("Devise is : " + countryService.devise());
	}

	@Override
	public void addCountry(String countryInfos, String continentCode) {
		Country country = stringToCountry(countryInfos);
		if(countryDAO.addCountry(country,continentCode)) {
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
	
	@Override
	public void getCountriesOfContinent(String ContinentCode) {
		List<Country> countriesOfContinent = countryDAO.getCountriesOfContinent(ContinentCode);
		if(countriesOfContinent == null) {
			System.out.println("Cette continent n'existe pas");
			return;
		}
		for(Country country : countriesOfContinent) {
			ICountryService countryService = applicationContext.getBean(ICountryService.class, country);
			System.out.println("NAME is   : " + countryService.name());
			System.out.println("WELCOME   : " + countryService.welcome());
			System.out.println("Devise is : " + countryService.devise());
			System.out.println("-----------------------------------------------");
		}
	}
	public void initData() {
		countryDAO.initData();
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
