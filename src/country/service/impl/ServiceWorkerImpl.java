package country.service.impl;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import country.dao.ContinentDAO;
import country.dao.CountryDAO;
import country.model.Country;
import country.service.IServiceWorker;

@Service
public class ServiceWorkerImpl implements IServiceWorker {
	@Autowired
	
	private CountryDAO countryDAO;
/*	@Autowired
	private ContinentDAO continentDAO;*/
	
	@Override
	public void AjouterCountry(Country country,String Continent) {
		
		
		
		    Scanner input1 = new Scanner(System.in);
		    String pays = input1.next();
		
		    try{
			
			String[] informations=pays.split(",");
			Country countryajout�=new Country();
			
			countryajout�.setCode(informations[0]);
			countryajout�.setName(informations[1]);
			countryajout�.setDevise(informations[2]);
			countryajout�.setGreetings(informations[3]);
			countryDAO.AjouterCountry(countryajout�,informations[4]);
			
		    }
		    catch (ArrayIndexOutOfBoundsException e) {
		    	System.err.println("Verifier la forme des informations du pays que vous avez ajouter !");
			}
		
		    
	
	}

	@Override
	public void dealWithCountryByCode(String code) {
		Country country=countryDAO.getByCode(code);
		
	    System.out.println("Voici des informations � propos du pays que vous avez entr� :");
	    System.out.println("-----------------------------------------------------------------------------------------------");
	    System.out.println("Code :"+country.getCode());
	    System.out.println("Pays :"+country.getName());
		System.out.println("Devise :"+country.getDevise());
		System.out.println("Salutation :"+country.getGreetings());
		System.out.println("Continent :"+country.getContinent().getName());
		
		}
	

	@Override
	public void supprimerCountry(String code) {
		countryDAO.supprimerByCode(code);
		
	}

	@Override
	public void modifierCountry(Country country,String code,String continent) {
		
		Scanner input1 = new Scanner(System.in);
	    String pays = input1.next();
	
	    try {
		String[] informations=pays.split(",");
		Country countrymodifi�=new Country();
		countrymodifi�.setCode(informations[0]);
		countrymodifi�.setName(informations[1]);
		countrymodifi�.setDevise(informations[2]);
		countrymodifi�.setGreetings(informations[3]);
		
		countryDAO.modifierByCode(countrymodifi�, code,informations[4]);
		
	    }catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Verifier la forme des informations du pays que vous avez modifier !");
		}
		
	
		
		countryDAO.modifierByCode(country, code,continent);
		
	}

	@Override
	public void ListerCountriesOfContinent(String code) {
		List<Country> countries=countryDAO.getCountrieByCode(code);
		if(countries.size()==0) 
			System.err.println("Le code du continent que vous avez entrer n'est pas valide !");
		else {
			System.out.println("Voici la liste des pays appartenant � ce continent :");
		for(Country country:countries) {
			
			System.out.println("---------------------------------------------------------------");
			System.out.println("Code :"+country.getCode());
			System.out.println("Pays :"+country.getName());
			System.out.println("Devise :"+country.getDevise());
			System.out.println("Salutation :"+country.getGreetings());
			
		}
		}	
	}
	 
	                

	

}