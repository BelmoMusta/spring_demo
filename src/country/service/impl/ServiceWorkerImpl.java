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

import java.util.Arrays;
import java.util.List;

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

	@Override
	public void modifierPays(String countryCode, String newFields) {
		Country country = countryDAO.getByCode(countryCode);
		Continent continent;
		List<String> newFieldsList = Arrays.asList(newFields.split(","));

		continent=continentDAO.getContientByCode(newFieldsList.get(4));

		country.setCode(newFieldsList.get(0));
		country.setName(newFieldsList.get(1));
		country.setDevise(newFieldsList.get(2));
		country.setGreetings(newFieldsList.get(3));
		country.setContinent(continent);
		countryDAO.modifierUnpays(country);
	}

	@Override
	public void ajouterPays(String info) {
		Country country = new Country();
		Continent continent = new Continent();

		String continentId = info.split(",")[4];
		continent=continentDAO.getContientByCode(continentId);

		country.setCode(info.split(",")[0]);
		country.setName(info.split(",")[1]);
		country.setDevise(info.split(",")[2]);
		country.setGreetings(info.split(",")[3]);
		country.setContinent(continent);
		countryDAO.enregistrerUnpays(country);
	}

	@Override
	public void supprimerPays(String code) {
		Country country = countryDAO.getByCode(code);
		countryDAO.supprimerUnpays(country);
	}
	@Override
	public Country getCountryData(String code) {
		Country pays = countryDAO.getByCode(code);
		ICountryService countryService = applicationContext.getBean(ICountryService.class, pays);
		System.out.println(pays.toString());
		return pays;
	}




	
	@Override
	public List<Country> getCountriesByContinent(String continentCode) {

		for( Country country : countryDAO.getCountriesByContinent(continentCode)){
			System.out.println(country.toString());
			System.out.println("\n");
		}
		return countryDAO.getCountriesByContinent(continentCode);
	}

}
