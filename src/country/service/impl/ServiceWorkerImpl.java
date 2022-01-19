package country.service.impl;

import country.dao.ContinentDAO;
import country.dao.CountryDAO;
import country.model.Continent;
import country.model.Country;
import country.service.IContinentService;
import country.service.ICountryService;
import country.service.IServiceWorker;
import country.service.IUtilityService;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class ServiceWorkerImpl implements IServiceWorker {
	@Autowired
	private CountryDAO countryDAO;
	@Autowired
	private ContinentDAO continentDAO;
	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	private IUtilityService utilityService;

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
						System.out
								.println("Insertion faite avec succès, pays inseré:\n\tCode: " + code + "\n\tNom: "
										+ name
										+ "\n\tDevise: " + devise + "\n\tGreeting: " + greetings + "\n\tContinent: "
										+ continent.getName());
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

	@Override
	public void deleteCountry(String countryCode) {
		Country country = countryDAO.getByCode(countryCode);
		if (country != null) {
			countryDAO.delete(country);
			System.out.println("Suppression faite avec succès.");
		} else
			System.out.println("Pays introuvable");
	}

	@Override
	public void dealWithContinentByCode(String code) {
		Continent continent = continentDAO.getContinentByCode(code);
		if (continent != null) {
			IContinentService continentService = applicationContext.getBean(IContinentService.class, continent);
			if (continentService.countries().size() > 0) {
				System.out.println("Pays du contient \"" + continent.getName() + "\" :");
				continentService.countries().forEach(country -> System.out.println("\t" + country.getName()));
			} else
				System.out.println(continent.getName() + " ne contient aucun pays.");
		} else
			System.out.println("Continent introuvable");

	}

	@Override
	public void updateCountry(String countryCode) {
		Country updatedCountry = countryDAO.getByCode(countryCode);
		if (updatedCountry != null) {
			updatedCountry = utilityService.updateSubMenu(updatedCountry);

			try {
				if (updatedCountry != null) {
					countryDAO.update(updatedCountry);
					System.out
							.println(
									"Modification faite avec succès, pays modifié:\n\tCode: " + updatedCountry.getCode()
											+ "\n\tNom: "
											+ updatedCountry.getName()
											+ "\n\tDevise: " + updatedCountry.getDevise() + "\n\tGreeting: "
											+ updatedCountry.getGreetings()
											+ "\n\tContinent: "
											+ updatedCountry.getContinent().getName());
				}
			} catch (DataIntegrityViolationException e) {
				System.out.println("Code pays dupliqué");
			}
		} else
			System.out.println("Pays introuvable !");
	}
}
