package country.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import country.dao.CountryDAO;
import country.model.Continent;
import country.model.Country;
import country.service.IServiceWorker;

@Service
public class ServiceWorkerImpl implements IServiceWorker {
	@Autowired
	private CountryDAO countryDAO;
	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public Country dealWithCountryByCode(String code) {
		Country pays = null;
		try {
			pays = countryDAO.getCountyByCode(code);

			System.out.print(" -Nom: " + pays.getName() + "\r\n");
			System.out.print(" -Devise: " + pays.getDevise() + "\r\n");
			System.out.println(" -Greetings: " + pays.getGreetings());
			// System.out.print(" -Code: " + pays.getCode() + "\r\n");

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("le code du pays {" + code + "} n'existe pas!!");
		}
		return pays;

	}

	@Override
	public void addNewCountry(String informations) {
		try {
			Country countrry = setCountry(informations);

			countryDAO.AddNewCountry(countrry);
		} catch (Exception e) {
			System.out.println(" la forme de la saisie des informations que vous avez entrées est incorrect.");
		}

	}

	@Override
	public void DeleteCountryByCode(String Code) {
		try {
			countryDAO.DeleteCountry(Code);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("le code du pays à supprimer:{" + Code + "} n'existe pas!!");
		}

	}

	@Override
	public Integer EditCountryByCode(String code, String ModifyInfos) {
		Country country = null;
		Integer NbrOfLignesModified = null;
		try {
			country = setCountry(ModifyInfos);
			NbrOfLignesModified = countryDAO.EditCountryInfos(code, country);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(" la forme de la saisie des informations que vous avez entrées est incorrect.");
		}

		return NbrOfLignesModified;

	}

	public Country setCountry(String informations) {
		String[] list = informations.split(",");

		Country country = new Country();
		if (list.length == 3) {
			country.setName(list[0]);
			country.setDevise(list[1]);
			country.setGreetings(list[2]);

		}
		if (list.length == 5) {
			country.setCode(list[0]);
			country.setName(list[1]);
			country.setDevise(list[2]);
			country.setGreetings(list[3]);
			country.setCodeContinent(list[4]);

		}

		return country;

	}

	@Override
	public void CountriesOfSameContinent(String codeContinent) {

		List<Country> ListOfCountries;
		Continent continent = countryDAO.GetContinent(codeContinent);
		ListOfCountries = countryDAO.CountriesOfContinent(codeContinent);
		System.out.println("============================== les Pays de la continent :" + continent.getName()
				+ " ===============================");
		for (Country country : ListOfCountries) {
			System.out.println("-->" + country.getName());
		}
		System.out.println(
				"================================================================================================");

	}

}
