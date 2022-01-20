package country.service.impl;

import country.dao.IContinentDAO;
import country.dao.ICountryDAO;
import country.model.Continent;
import country.model.Country;
import country.service.IServiceWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

@Service
public class ServiceWorkerImpl implements IServiceWorker {
	@Autowired
	private ICountryDAO countryDAO;

	@Autowired
	private IContinentDAO continentDAO;

	@Override
	public void dealWithMenuChoice(String choix) {
		switch (choix) {
			case "1" : this.choiceAddCountry();
				break;
			case "2" : this.choiceShowCountry();
				break;
			case "3" : this.choiceDeleteCountry();
				break;
			case "4" : this.choiceEditCountry();
				break;
			case "5" : this.choiceShowCountriesByCountry();
				break;
			default: break;
		}
	}

	@Override
	public void showMenu() {
		System.out.println("**************************************");
		System.out.println("List des commandes : ");
		System.out.println("** Pour l'ajout d'un nouveau pays, tapper 1");
		System.out.println("** Pour lister les informations d'un pays, tapper 2");
		System.out.println("** Pour supprimer un pays, tapper 3");
		System.out.println("** Pour modifier des informations d'un pays, tapper 4");
		System.out.println("** Pour lister tous les pays d'un continent, tapper 5");
		System.out.println("** Pour sortir de l'application, tapper 0");
	}

	private void choiceAddCountry() {
		System.out.println("Veuillez saisir les informations du pays (ex :FR,france,EURO,Bonjour!,Europe) : ");
		Scanner inputFromConsole = new Scanner(System.in);
		String input = inputFromConsole.nextLine();
		input.replaceAll("\\s+","-");
		System.out.println(input);
		String[] informations = input.split("[,]");
		if(informations.length != 5) {
			System.out.println("** Veuillez saisir les cinq informations.");
		} else {
			Continent continent = continentDAO.getByName(informations[4].replace("-","\\s+"));

			if(continent != null) {
				Country countryExist = countryDAO.getByCode(informations[0].toUpperCase(Locale.ROOT));

				if(countryExist != null) {
					System.out.println("** Un pays existe déja avec le code suivant : " + informations[0].toUpperCase(Locale.ROOT));
					System.out.println("** les informations du pays :");
					System.out.println(countryExist);
					return;
				}

				Country country = new Country();
				country.setCode(informations[0].toUpperCase(Locale.ROOT));
				country.setName(informations[1].replace("-","\\s+").substring(0, 1).toUpperCase() + informations[1].substring(1));
				country.setDevise(informations[2].replace("-","\\s+"));
				country.setGreetings(informations[3].replace("-","\\s+"));
				country.setContinent(continent);
				this.countryDAO.add(country);
			} else {
				System.out.println("** Le continent n'existe pas.");
				System.out.println("** Liste des continents.");
				for(Continent continent1: continentDAO.getAll()) {
					System.out.println("* " + continent1.getName());
				}
			}
		}
	}

	private void choiceShowCountry() {
		System.out.println("Veuillez saisir le code du pays recherché (ex :FR) :");
		Scanner inputFromConsole = new Scanner(System.in);
		String input = inputFromConsole.nextLine();
		Country returned = this.countryDAO.getByCode(input.toUpperCase());
		if(returned == null) {
			System.out.println("Aucun pays trouvé");

		} else {
			System.out.println(returned);
		}
	}

	private void choiceDeleteCountry() {
		System.out.println("Veuillez saisir le code du pays à supprimer (ex :FR) :");
		Scanner inputFromConsole = new Scanner(System.in);
		String input = inputFromConsole.nextLine();
		Country countryDeleted = this.countryDAO.deleteByCode(input.toUpperCase());
		if(countryDeleted != null) System.out.println(countryDeleted.getName() + " a été supprimé");
		else System.out.println("Pays avec le code " + input.toUpperCase() + " non trouvé");
	}

	private void choiceShowCountriesByCountry() {
		System.out.println("Veuillez saisir le nom du continent (ex :Europe) :");
		Scanner inputFromConsole = new Scanner(System.in);
		String input = inputFromConsole.nextLine();
		Continent continent = continentDAO.getByName(input);

		if(continent != null) {
			List<Country> countries = countryDAO.getByContinent(continent);
			for(Country country: countries) {
				System.out.println(country);
			}
		} else {
			System.out.println("** Le continent n'existe pas.");
			System.out.println("** Liste des continents.");
			for(Continent continent1: continentDAO.getAll()) {
				System.out.println("* " + continent1.getName());
			}
		}
	}

	private void choiceEditCountry() {
		System.out.println("Veuillez saisir le code du pays à modifier (ex :FR) :");
		Scanner inputFromConsole = new Scanner(System.in);
		String input1 = inputFromConsole.nextLine();
		Country countryToEdit = this.countryDAO.getByCode(input1.toUpperCase());
		if(countryToEdit != null) {
			System.out.println("Veuillez saisir les nouveau informations du pays (ex :FR,france,EURO,Bonjour!,Europe) : ");
			String input2 = inputFromConsole.nextLine();
			String[] informations = input2.split("[,]");
			if(informations.length != 5) {
				System.out.println("** Veuillez saisir les cinq informations.");
			} else {
				Continent continent = continentDAO.getByName(informations[4]);
				if(continent != null) {
					Country country = new Country();
					country.setCode(informations[0].toUpperCase(Locale.ROOT));
					country.setName(informations[1].substring(0, 1).toUpperCase() + informations[1].substring(1));
					country.setDevise(informations[2]);
					country.setGreetings(informations[3]);
					country.setContinent(continent);
					this.countryDAO.update(country);
				} else {
					System.out.println("** Le continent n'existe pas.");
					System.out.println("** Liste des continents.");
					for(Continent continent1: continentDAO.getAll()) {
						System.out.println("* " + continent1.getName());
					}
				}
			}

		} else {
			System.out.println("Pays avec le code " + input1.toUpperCase() + " non trouvé");
		}
	}
}
