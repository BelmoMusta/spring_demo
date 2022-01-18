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
		System.out.println("Devise is :" + countryService.devise());
	}

	@Override
	public void AddCountry() {
		
		Country country =new Country();
		System.out.println("Enter the contry informations Ex:(FR,france,EURO,Bonjour!) :");
		@SuppressWarnings("resource")
		Scanner inputFromConsole = new Scanner(System.in);
		String countryString = inputFromConsole.next();
		String[] arrOfStr = countryString.split(",");
		 country.setCode(arrOfStr[0]);
		 country.setName(arrOfStr[1]);
		 country.setDevise(arrOfStr[2]);
		 country.setGreetings(arrOfStr[3]);
		countryDAO.addCountry(country);
		
		
	}

	@Override
	public void DeleteCountry() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unused")
		Country country =new Country();
		System.out.println("Enter the code of the country (Ex:fr) :");
		@SuppressWarnings("resource")
		Scanner inputFromConsole = new Scanner(System.in);
		String countryString1 = inputFromConsole.next();
		
		countryDAO.deleteCountry(countryString1);
	}

	@Override
	public void GetList() {
		
		countryDAO.getAllCountries();
		
	}

	@SuppressWarnings("unused")
	@Override
	public void UpdateCountry() {
		// TODO Auto-generated method stub
		Country country = new Country();
		System.out.println("Enter the code of the country (Ex:fr) :");
		@SuppressWarnings("resource")
		Scanner inputFromConsole = new Scanner(System.in);
		String Code = inputFromConsole.next();
		System.out.println("Enter the informations of the country (FR,france,EURO,Bonjour!) :");
		@SuppressWarnings("resource")
		Scanner inputFromConsole1 = new Scanner(System.in);
		String countryString = inputFromConsole1.next();
		String[] arrOfStr = countryString.split(",");
		 country.setCode(arrOfStr[0]);
		 country.setName(arrOfStr[1]);
		 country.setDevise(arrOfStr[2]);
		 country.setGreetings(arrOfStr[3]);
		 countryDAO.updateCountry(Code, country);
	}
	
	
}
