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

		Country pays = countryDAO.getByCode(language);

		// car c'est prototype
		ICountryService countryService = applicationContext.getBean(ICountryService.class, pays);

		System.out.println("WELCOME : " + countryService.welcome());
		System.out.println("Devise is :" + countryService.devise());
	}

	@Override
	public void addNewCountry(String data) {

		Country country = new Country();


		String[] dataArr = data.split(",");


		country.setCode(dataArr[0]);
		country.setName(dataArr[1]);
		country.setDevise(dataArr[2]);
		country.setGreetings(dataArr[3]);


		Continent continent = continentDAO.getContient(dataArr[4]);
		country.setContinent(continent);
		countryDAO.addNewCountry(country);

	}

	@Override
	public void getCountryData(String code) {

		Country country = countryDAO.getByCode(code);

		ICountryService countryService = applicationContext.getBean(ICountryService.class, country);

		System.out.print(countryService.name() + "," + countryService.welcome() + "," + countryService.devise() + "," + countryService.continent() + "\n");


	}

	@Override
	public void deletCountry(String Code) {

		Country country = countryDAO.getByCode(Code);
		countryDAO.deleteCountry(country);

	}

	@Override
	public void updateCountry(String Code, String update) {

		Country country = countryDAO.getByCode(Code);

		String[] newfields = update.split(",");



		country.setCode(newfields[0]);
		country.setName(newfields[1]);
		country.setDevise(newfields[2]);
		country.setGreetings(newfields[3]);

		Continent continent=continentDAO.getContient(newfields[4]);
		country.setContinent(continent);
		countryDAO.updateCountry(country);
	}

	@Override
	public void getCountries(String code) {

		for( Country country : countryDAO.getCountries(code)){

			System.out.println("\n");
			System.out.println(country.getCode()+","+country.getName()+","+country.getDevise()+","+country.getContinent().getName());
			System.out.println("\n");

		}

	}

}
