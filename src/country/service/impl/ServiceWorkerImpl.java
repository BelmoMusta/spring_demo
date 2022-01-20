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

import java.util.Scanner;

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
	public Continent getContientByName(String name) {
		if (continentDAO.existsByName(name)) {
			System.out.println(continentDAO.getContientByName(name).toString());
			return continentDAO.getContientByName(name);
		} else {
			System.out.println("Le nom du continent entré n'est pas valide");
			return null;
		}
	}

	@Override
	public void deleteCountryByCode(String code) {
		if(countryDAO.exist(code)){
			Country country = countryDAO.getByCode(code);
			countryDAO.delete(country);
		}else{
			System.out.println("le code saisie ne correspand à aucun pays ");
		}
	}

	@Override
	public void getContinentCountries(String code) {
		if(continentDAO.exists(code)){
			continentDAO.getContientByCode(code).getCountries().forEach(System.out::println);
		}else {
			System.out.println("veuiller saisir un code de continent valide (eur,afr,ame,aus,asia");
		}
	}

	@Override
	public void updateCountry(String code, Scanner scanner) {
		boolean isModified = false;

		if(countryDAO.exist(code)){
			Country country = countryDAO.getByCode(code);

			//afficher les infos du pays à modifier
			System.out.println(" \n Les informations actuelles du pays :");
			System.out.println(country.toString() +"\n");

			//recupperation de la valeur des champs à modifier
			System.out.println("veuiller saisir les informations à modifier (Pour ne pas modifier un champs veuillez taper '*'):");

			System.out.print("Nom :");
			String name = scanner.next();
			if(!name.equals("*")){
				country.setName(name);
				isModified = true;
			}

			System.out.print("devise :");
			String devise = scanner.next();
			if(!devise.equals("*")){
				country.setDevise(devise);
				isModified = true;
			}

			System.out.print("greetings :");
			String greetings = scanner.next();
			if(!greetings.equals("*")){
				country.setGreetings(greetings);
				isModified = true;
			}

			System.out.print("continent :");
			String continentName = scanner.next();
			if(!continentName.equals("*")){
				if(continentDAO.existsByName(continentName)) {
					Continent continent = continentDAO.getContientByName(continentName);
					country.setContinent(continent);
					isModified = true;
				}else{
					System.out.println("Le continent n'existe pas dans la base");
				}
			}

			if(isModified){
				//confirmation du changement avant le rendre persistant
				System.out.println("\n Etat actuel du pays");
				System.out.println(country.toString());

				System.out.print(" \npour confirmer les modifications effectues entrez 'c' sinon appuyez n'importe quelle autre caractere: ");
				String confirmation = scanner.next();
				if(confirmation.equals("c")) {
					//update
					countryDAO.update(country);

					//afficher les  infos du pays apres la modif
					System.out.println("\n le pays apres les modifications");
					System.out.println(country.toString());
				}
			}else{
				System.out.println("Le pays n'a pas été modifié");
			}



		}else{
			System.out.println("le code saisie ne correspand à aucun pays");
		}

	}

}
