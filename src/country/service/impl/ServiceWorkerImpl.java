package country.service.impl;

import country.dao.ContinentDAO;
import country.dao.CountryDAO;
import country.model.Continent;
import country.model.Country;
import country.service.ICountryService;
import country.service.IServiceWorker;

import java.util.List;

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
	
	//Aspect fonctionnel 1
	@Override
	public void dealWithAddCountry(Country country,String nomContinet) {
		if(!countryDAO.exist(country.getCode()) 
				&& country.getCode() != null && country.getCode() != ""
				&& country.getName() != null && country.getName() != ""
				&& continentDAO.exist(nomContinet)) {
			countryDAO.addCountry(country,nomContinet);
			System.out.println("Ajout avec succès");
		}else {
			System.err.println("Pays existant ou Continent introuvable ou Entrée invalide");
		}
	}
	
	//Aspect fonctionnel 2
		@Override
		public void dealWithCountryByCode(String code) {
			if(countryDAO.exist(code)) {
				Country pays = countryDAO.getByCode(code);
				
				// car c'est prototype
				ICountryService countryService = applicationContext.getBean(ICountryService.class, pays);
				
				System.out.println("Pays : " + countryService.name());
				System.out.println("Salut : " + countryService.welcome());
				System.out.println("Devise : " + countryService.devise());
			}else {
				System.err.println("Pays introuvable !");
			}
		}

	//Aspect fonctionnel 3
	@Override
	public void dealWithDeleteCountry(String code) {
		if(countryDAO.exist(code)) {
			countryDAO.deleteCountry(code);
			System.out.println("Suppression avec succée !");
		}else {
			System.err.println("Pays introuvable !");
		}	
	}
	
	//Aspect fonctionnel 4
	@Override
	public void dealWithUpdateCountry(String code, Country country, String nomContinet) {
		if(countryDAO.exist(code)) {
			if(country.getCode() != null && country.getCode() != ""
					&& country.getName() != null && country.getName() != ""
					&& continentDAO.exist(nomContinet)) {
				if(code != country.getCode() && countryDAO.exist(country.getCode())) {
					System.err.println(country.getCode() + ": deja existant !");
				}else {
					countryDAO.updateCountry(code, country, nomContinet);
					System.out.println("Modification avec succès");
				}
			}else {
				System.err.println("Pays existant ou Continent introuvable ou Entrée invalide");
			}
			
		}else {
			System.err.println("code ne correspond a aucun pays");
		}
		
	}
	
	//Aspect fonctionnel 5
	@Override
	public void dealwWithSelectCountriesOfContinent(String code) {
		Continent continent = continentDAO.getContinentByCode(code);
		if(continent != null) {
			List<Country> countries = continentDAO.getCountriesByContinentCode(code);
			if(countries.size() != 0) {
				for(Country country : countries) {
					System.out.println("Nom : "+country.getName());
					System.out.println("Code : "+country.getCode());
					System.out.println("Devise : "+country.getDevise());
					System.out.println("Salut : "+country.getGreetings());
					System.out.println("*********");
				}
			}else {
				System.out.println("Aucun pays dans ce continent");
			}
		}else {
			System.err.println("Continent introuvable !");
		}
		
	}
	
}
