package country.service.impl;

import country.dao.ICountryDAO;
import country.model.Country;
import country.service.IServiceWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class ServiceWorkerImpl implements IServiceWorker {
	@Autowired
	private ICountryDAO countryDAO;

	@Override
	public void dealWithMenuChoice(String choix) {
		switch (choix) {
			case "1" : this.choiceAddCountry();
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
		System.out.println("Veuillez saisir les informations du pays (ex :FR,france,EURO,Bonjour!) : ");
		Scanner inputFromConsole = new Scanner(System.in);
		String input = inputFromConsole.next();
		String[] informations = input.split("[,]");
		if(informations.length != 4) {
			System.out.println("** Veuillez saisir les quatres informations.");
		} else {
			Country country = new Country();
			country.setCode(informations[0]);
			country.setName(informations[1]);
			country.setDevise(informations[2]);
			country.setGreetings(informations[3]);

			this.countryDAO.add(country);
		}
	}
}
