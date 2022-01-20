package country.service.impl;

import country.dao.ContinentDAO;
import country.dao.CountryDAO;
import country.model.Continent;
import country.model.Country;
import country.service.ICountryService;
import country.service.IServiceWorker;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


	public void addCountry(String country){
		String regex = "/(([A-Za-z])\\w+,){4}([A-Za-z])\\w+/g";
		Pattern pattern = Pattern.compile(regex) ;
		Matcher matcher = pattern.matcher(country) ;
		if(matcher.matches()){
			String[] countryParts = country.split(",");
			String code = countryParts[0];
			String name = countryParts[1];
			String device = countryParts[2];
			String greeting = countryParts[3];
			String continentName = countryParts[4];

			if(continentDAO.existsByName(continentName)){
				Continent continent = continentDAO.getContientByName(countryParts[4]);
				Country c = new Country();
				c.setCode(code);
				c.setDevise(device);
				c.setName(name);
				c.setGreetings(greeting);
				c.setContinent(continent);
				try{
					countryDAO.save(c);
				}catch (ConstraintViolationException e){
					System.out.println("\nle pays existe deja dans la bd !! (code pays dupliqué)");
				}
			}else{
				System.out.println("\nLe nom du continent saisie est invalide!!");
			}
		}else{
			System.out.println("\nFormat du pays saisie invalide !!");
			System.out.println("veuiller respecter le format suivant (code,nom,devise,greetings,nom du continent)");
		}



	}

	public void getAllCountries(){
		countryDAO.getALl().forEach(c -> System.out.println(c.toString()));
	}

	@Override
	public void getContientByName(String name) {
		System.out.println(continentDAO.getContientByName(name).toString());;
	}
}
