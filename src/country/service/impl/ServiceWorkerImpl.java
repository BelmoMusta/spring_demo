package country.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import country.dao.ContinentDAO;
import country.dao.CountryDAO;
import country.model.Continent;
import country.model.Country;
import country.service.ICountryService;
import country.service.IServiceWorker;

@Service
public class ServiceWorkerImpl implements IServiceWorker {
	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	private CountryDAO countryDAO;
	@Autowired
	private ContinentDAO continentDAO;
	
	
	@Override
	public void dealWithCountryByCode(String language) {
		if(countryDAO.exists(language)){
			Country pays = countryDAO.getByCode(language);

			// car c'est prototype
			ICountryService countryService = applicationContext.getBean(ICountryService.class, pays);

			System.out.println("WELCOME : " + countryService.welcome());
			System.out.println("Devise is :" + countryService.devise());
			System.out.println("Continent is :" + countryService.getContinentName());
		}else {
			System.out.println("le code entré ne correspand à aucun pays ");
		}
	}


	public void addCountry(String country){
		String[] countryParts = country.split(",");
		String code = countryParts[0];
		String name = countryParts[1];
		String device = countryParts[2];
		String greeting = countryParts[3];
		Continent continent = continentDAO.getByName(countryParts[4]);

		Country c = new Country();
		c.setCode(code);
		c.setDevise(device);
		c.setName(name);
		c.setGreetings(greeting);
		c.setContinent(continent);
		countryDAO.saveCountry(c);
	}

	public void getAllCountries(){
		((Iterable<Country>) countryDAO.getALL()).forEach(c -> System.out.println(c.toString()));
	}

	@Override
	public Continent getContinentByName(String name) {
		System.out.println(continentDAO.getByName(name).toString());;
		return continentDAO.getByName(name);
	}

	@Override
	public void deleteCountryByCode(String code) {
		if(countryDAO.exists(code)){
			countryDAO.deleteCountry(code);
		}else{
			System.out.println("le code entré ne correspand à aucun pays ");
		}
	}

	@Override
	public void getContinentCountries(String code) {
		if(continentDAO.exist(code)){
			continentDAO.getByCode(code).getCountries().forEach(System.out::println);
		}else {
			System.out.println("entrer un code de continent valide (afr,eur,asi,am,aus");
		}
	}


	@Override
	public void updateCountry(Country country) {
		countryDAO.update(country);
	}


	@Override
	public void updateCountry(String country) {
		    String[] infos=country.split(",");
		    Country c=new Country();
			countryDAO.update(c);
			ICountryService countryService = applicationContext.getBean(ICountryService.class, c);

			System.out.println("WELCOME : " + countryService.welcome());
			System.out.println("Devise is :" + countryService.devise());
			System.out.println("Continent is :" + countryService.getContinentName());
		}
}

