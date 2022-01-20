package country.service.impl;

import country.dao.CountryDAO;

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
	public void ajouterCountry(Country _country, String _nameContinent) {
		countryDAO.ajouter(_country,_nameContinent);
		
	}

	@Override
	public void afficheCountry(String code) {
		Country country=countryDAO.getByCode(code);
		if(country==null) System.err.println("n'existe pas country avec ce code");
		else {  
	    System.out.println("########### AFFICHAGE ###########");
	    System.out.println("Id de country :"+country.getId());
		System.out.println("Name de country :"+country.getName());
		System.out.println("Greeting de country:"+country.getGreetings());
		System.out.println("Code de country :"+country.getCode());
		System.out.println("Devise de country :"+country.getDevise());
		System.out.println("Continent de country :"+country.getContinent().getName());
		}
	}

	@Override
	public void SuppCountry(String _inputCode) {
		int rowsAffected = countryDAO.SuppByCode(_inputCode);
		if (rowsAffected == -2)
			System.err.println("pas country avec ce code ");
		else if (rowsAffected > 0) {
			System.out.println("Supprimer " + rowsAffected + " ligne.");
		} else System.err.println("not deleted");
	}
	@Override
	public void ModifyCountry(Country country, String _inputCode, String _nameContinet) {
		int rowsAff = countryDAO.updateByCode(country, _inputCode, _nameContinet);
		if (rowsAff > 0) System.out.println("Updates " + rowsAff + " rows.");
		
		else if (rowsAff == -1)System.err.println("there is no continent with this name ");
		
		else if (rowsAff == -2) System.err.println("code déja existe");
		
		else System.err.println("not updated");
	}
	@Override
	public void selectCountriesByContinent(String _inputcode) {
		List<Country> countries = countryDAO.getCountrieByCode(_inputcode);
		if (countries == null)
			System.err.println("pas  continent avec ce code");
		else if (countries.size() == 0)
			System.err.println(" pas de contry dans ce continent");
		else {
			System.out.println("#############      AFFICHAGE DES CONTRY    ##############");
			for (Country country : countries) {
				System.out.println("Id :" + country.getId());
				System.out.println("Name :" + country.getName());
				System.out.println("Code :" + country.getCode());
				System.out.println("Devise :" + country.getDevise());
				System.out.println("Greeting :" + country.getGreetings());
				System.out.println("##########################################");
			}
		}
	}
}
