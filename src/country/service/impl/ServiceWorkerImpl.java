package country.service.impl;

import country.dao.CountryDAO;
import country.model.Country;
import country.service.ICountryService;
import country.service.IServiceWorker;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
		System.out.println("Devise is : " + countryService.devise());
	}
	
	@Override
	public void addCountry(String infos) {
		String[] countryInfos = infos.split(",", 5);
		if(checkIfCountryExists(countryInfos[1])) System.out.println("Pays déja existant!");
		else{
			countryDAO.addElement(countryInfos);
			System.out.println("Pays ajouté avec succés!!"); 
		}
	}
	
	@Override
	public void deleteCountry(String code) {
		countryDAO.deleteElement(code);
		System.out.println("Pays supprimé avec succés!!"); 
	}
	
	@Override
	public void updateCountry(String code, String[] countryNInfos) {
		countryDAO.updateElement(code, countryNInfos);
		System.out.println("Pays modifié avec succés!!"); 
	}
	
	@Override
	public void countries(String continentCode) {
		System.out.print(countryDAO.getContinentCountries(continentCode));
	}
	
	@Override
	public boolean checkIfCountryExists(String code) {
		return countryDAO.checkExistance(code); //return true if the element exists in the table
	}
	
	@Override
	public boolean checkIfContinentExists(String Ccode) {
		return countryDAO.checkContinentExistance(Ccode);
	}
	
	@Override
	public List<String> countryElements(String countryCode){
		return countryDAO.countryElements(countryCode);
	}
	
}
