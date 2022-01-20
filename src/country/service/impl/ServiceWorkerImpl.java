package country.service.impl;

//import country.dao.ContinentDAO;
import country.dao.CountryDAO;
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
	private ApplicationContext applicationContext;

	public void dealWithCountryByCode(String language) {
		// TODO Auto-generated method stub
		Country pays = countryDAO.getByCode(language);
		// car c'est prototype
		ICountryService countryService = applicationContext.getBean(ICountryService.class, pays);
		
		System.out.println("WELCOME : " + countryService.welcome());
		System.out.println("Devise is :" + countryService.devise());
		
	}

	public void SaveCountryByInformations(String Informations) {
		// TODO Auto-generated method stub
		String[] InfosUser = Informations.split(",");
		Country c =new Country(InfosUser[0],InfosUser[1],InfosUser[2],InfosUser[3],InfosUser[4]);
		countryDAO.SaveCountry(c);
		System.out.println("pays ajouté");
		
	}

	public void dealWithDeleteCountry(String code) {
		// TODO Auto-generated method stub
		countryDAO.DeleteCountry(code);
		System.out.println("pays supprimé");
		
	}

	public void UpdateCountryByInformations(String lang, String Informations) {
		// TODO Auto-generated method stub
		String[] InfosUser = Informations.split(",");
		Country c =new Country(InfosUser[0],InfosUser[1],InfosUser[2],InfosUser[3],InfosUser[4]);
		countryDAO.UpdateCountry(lang, c);
		System.out.println("pays modifié");
		
	}

	public void CountriesOfContinent(String codeContinent) {
		// TODO Auto-generated method stub
		List<Country> l = countryDAO.ListCountries(codeContinent);
		l.toString();
		for(Country c: l) {
			System.out.print(c.getName());
			System.out.println();
		}
		
	}
	
}
