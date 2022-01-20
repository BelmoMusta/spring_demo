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
		if(countryDAO.exist(language)){
			Country pays = countryDAO.getByCode(language);

			// car c'est prototype
			ICountryService countryService = applicationContext.getBean(ICountryService.class, pays);

			System.out.println("WELCOME : " + countryService.welcome());
			System.out.println("Devise is :" + countryService.devise());
			System.out.println("Continent is :" + countryService.continent());
		}else {
			System.out.println("le code saisie ne correspand à aucun pays ");
		}
	}


	public void addCountry(String country){
		String[] countryParts = country.split(",");
		String code = countryParts[0];
		String name = countryParts[1];
		String device = countryParts[2];
		String greeting = countryParts[3];
		Continent continent = continentDAO.getContientByName(countryParts[4]);

		Country c = new Country();
		c.setCode(code);
		c.setDevise(device);
		c.setName(name);
		c.setGreetings(greeting);
		c.setContinent(continent);
		countryDAO.save(c);
	}

	public void getAllCountries(){
		countryDAO.getALl().forEach(c -> System.out.println(c.toString()));
	}

	@Override
	public void getContientByName(String name) {
		System.out.println(continentDAO.getContientByName(name).toString());;
	}

	@Override
	public void deleteCountryByCode(String code) {
		if(countryDAO.exist(code)){
			Country country = countryDAO.getByCode(code);
			countryDAO.delete(country);
			System.out.println("\npays supprimé avec succes.");
		}else{
			System.out.println("\nle code saisie ne correspand à aucun pays ");
		}
	}
}
