package country.service.impl;

import country.dao.ContinentDAO;
import country.dao.CountryDAO;
import country.model.Continent;
import country.model.Country;
import country.service.ICountryService;
import country.service.IServiceWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceWorkerImpl implements IServiceWorker {

	@Autowired
	private CountryDAO countryDAO;
	@Autowired
	private ContinentDAO continentDAO;
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
	public void addCountry(String information) {
		Country country = new Country();
		country.setCode(information.split(",")[0]);
		country.setName(information.split(",")[1]);
		country.setDevise(information.split(",")[2]);
		country.setGreetings(information.split(",")[3]);
		Continent continent = new Continent();
		String code = information.split(",")[4];
		continent=continentDAO.getContientByCode(code);
		country.setContinent(continent);
		countryDAO.saveCountry(country);
	}

	@Override
	public Country getCountryByCode(String code) {
		Country pays = countryDAO.getByCode(code);
		return pays;
	}

	@Override
	public Country getInfos(String code) {
		Country pays = countryDAO.getByCode(code);
		System.out.println("name : " + pays.getName());
		System.out.println("code :" + pays.getCode());
		System.out.println("WELCOME : " + pays.getGreetings());
		System.out.println("Devise :" + pays.getDevise());
		System.out.println("Continent :" + pays.getContinent().getName());
		return pays;
	}

	@Override
	public void deleteCountry(String code) {
		Country country = countryDAO.getByCode(code);
		countryDAO.deleteCountry(country);

	}

	@Override
	public void updateCountry(String code, String information) {
		Country country = countryDAO.getByCode(code);
		country.setCode(information.split(",")[0]);
		country.setName(information.split(",")[1]);
		country.setDevise(information.split(",")[2]);
		country.setGreetings(information.split(",")[3]);
		Continent continent = new Continent();
		String codeContinent = information.split(",")[4];
		continent=continentDAO.getContientByCode(codeContinent);
		country.setContinent(continent);
		countryDAO.updateCountry(country);

	}

	@Override
	public List<Country> getCountries(String code) {
		List<Country> list = countryDAO.getCountries(code);
		for( Country pays : list){
			System.out.println("name : " + pays.getName());
			System.out.println("code :" + pays.getCode());
			System.out.println("WELCOME : " + pays.getGreetings());
			System.out.println("Devise :" + pays.getDevise());
			System.out.println("Continent :" + pays.getContinent().getName());
			System.out.println("\n");
		}
		return list;
	}


}
