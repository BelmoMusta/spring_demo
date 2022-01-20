package country.service.impl;

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

	@Override
	public void getCountryByCode(String language) {
		Country pays = countryDAO.getCountryByCode(language);
		// car c'est prototype
		ICountryService countryService = applicationContext.getBean(ICountryService.class, pays);

		System.out.println("Nom : " + countryService.name());
		System.out.println("Continent :" + countryService.continent());
		System.out.println("Devise :" + countryService.devise());
		System.out.println("greetings :" + countryService.welcome());
	}

	@Override
	public void getAllCountries() {
		countryDAO.getAllCountries();
	}

	public void addCountry(String countryData) {
		countryDAO.addCountry(getContryFromData(countryData));
		System.out.println("le pays a été ajouter avec success");
	}

	public void updateCountry(String coutryCode, String countryData) {
		countryDAO.updateByCode(coutryCode, getContryFromData(countryData));
		System.out.println("le pays a été modifier avec success");
	}

	public Country getContryFromData(String countryData) {
		Country country = new Country();
		String[] data = countryData.split(",");
		country.setCode(data[0]);
		country.setName(data[1]);
		country.setContinent(data[2]);
		country.setDevise(data[3]);
		country.setGreetings(data[4]);
		return country;
	}

	public void removeCountry(String CountryCode) {
		countryDAO.removeCountry(CountryCode);
		System.out.println("le pays a été supprimer avec success");
	}

	public void getAllCountriesInContinent(String continentCode) {
		List<Country> countries = countryDAO.getAllCountriesInContinent(continentCode);
		String continentName = countryDAO.getContinentName(continentCode);
		System.out.println("les pays que se trouvent dans le continent " + continentName + " sont:");
		for (Country country : countries) {
			System.out.println("- " + country.getName());
		}
	}
}
