package country.service.impl;

import country.dao.ContinentDAO;
import country.dao.CountryDAO;
import country.model.Continent;
import country.model.Country;
import country.service.ICountryService;
import country.service.IServiceWorker;

import org.hibernate.exception.ConstraintViolationException;
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
	public void dealWithCountryByCode(String code) {
		Country pays = countryDAO.getByCode(code);
		// car c'est prototype
		if (pays != null) {
			ICountryService countryService = applicationContext.getBean(ICountryService.class, pays);

			System.out.println("Nom :" + countryService.nom());
			System.out.println("Greeting : " + countryService.welcome());
			System.out.println("Devise:" + countryService.devise());
			System.out.println("Continent: " + countryService.continent().toString());
		} else {
			System.out.println("Pays introuvable !");
		}

	}

	@Override
	public void addCountry(String country) {

		String[] countryInfo = country.split(",");
		if (countryInfo.length == 5) {

			String code = countryInfo[0];
			String name = countryInfo[1];
			String devise = countryInfo[2];
			String greetings = countryInfo[3];

			if (code.length() > 0 && name.length() > 0 && devise.length() > 0 && greetings.length() > 0) {

				Continent continent = continentDAO.getContinentByCode(countryInfo[4]);
				if (continent != null) {
					Country countryToAdd = new Country();
					countryToAdd.setCode(code);
					countryToAdd.setName(name);
					countryToAdd.setDevise(devise);
					countryToAdd.setGreetings(greetings);
					countryToAdd.setContinent(continent);
					try {
						countryDAO.save(countryToAdd);
						System.out.println("Insertion faite avec succes, pays insere:\n" + countryToAdd.toString());
					} catch (ConstraintViolationException e) {
						System.out.println("Code pays existant");
					}

				} else
					System.out.println("Code Continent errone");
			} else
				System.out.println("Format invalide");
		} else
			System.out.println("Format invalide");

	}
}
