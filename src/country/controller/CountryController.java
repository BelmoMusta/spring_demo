package country.controller;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import country.model.Country;
import country.service.IServiceWorker;

@Component
public class CountryController {
	@Autowired
	private IServiceWorker serviceWorker ;
	
	
	
	public void exitApp() {
		System.out.println("\n\n");
		System.out.println("=============");
		System.out.println("Bay Bay......");
		System.out.println("=============");
		System.exit(0);
	}
	
	public void addCountry(Scanner inputFromConsole) {
		System.out.println("Ajouter un nouveau pays (code,nom,devise,salutation): ");
		while (true) {
			if (inputFromConsole.hasNext()) {
				String pays = inputFromConsole.next();
				String[] _pays = pays.split(",");
				if (_pays.length != 4) {
					System.out.print("!! le format tappait est inccorect , essayer à nouveau : ");
					continue;
				} else {
					Country country = serviceWorker.getCountry(_pays[0]);
					if (country == null) {
						country = new Country();
						country.setCode(_pays[0]);
						country.setName(_pays[1]);
						country.setDevise(_pays[2]);
						country.setGreetings(_pays[3]);

						serviceWorker.addCountry(country);
						return;
					} else {
						System.out.println("Ce pays et deja existe....");
						return;
					}
				}

			}
			return;
		}
	}
	
	public void getCountry(Scanner inputFromConsole) {
		System.out.print("Tappez le code pays que vous voulez lister ses informations: ");
		while (true) {
			if (inputFromConsole.hasNext()) {
				String code = inputFromConsole.next();
				Country country = serviceWorker.getCountry(code);
				if (country == null) {
					System.out.print("Le pays n'existe pas , tappez un autre code correct : ");
				} else {
					System.out.println(country.toString());
					return;
				}

			}
		}
	}
	
	public void deleteCountry(Scanner inputFromConsole) {
		System.out.print("Tappez le code pays que vous voulez supprimer : ");
		while (true) {
			if (inputFromConsole.hasNext()) {
				String code = inputFromConsole.next();
				Country country = serviceWorker.getCountry(code);
				if (country == null) {
					System.out.print("Le pays n'existe pas , tappez un autre code correct : ");
				} else {
					serviceWorker.deleteCountry(code);
					System.out.println("Le pays est bien supprimer ");
					return;
				}

			}
		}
	}
	
	public void updateCountry(Scanner inputFromConsole) {
		System.out.print("Entrer le code de pays que vous souhaitez modifier : ");
		while (true) {
			if (inputFromConsole.hasNext()) {

				String code = inputFromConsole.next();
				Country country = serviceWorker.getCountry(code);
				if (country == null) {
					System.out.print("Le pays n'existe pas , tappez un autre code correct :");
					continue;
				} else {
					System.out.print(String.format("Tappez les nouveaux informations de pays [%s] : ",
							country.getName()));
					while (inputFromConsole.hasNext()) {
						String pays = inputFromConsole.next();
						String[] _pays = pays.split(",");
						if (_pays.length != 4) {
							System.out.print("!! le format tappait est inccorect , essayer à nouveau : ");
						} else {
							country = new Country();
							country.setCode(_pays[0]);
							country.setName(_pays[1]);
							country.setDevise(_pays[2]);
							country.setGreetings(_pays[3]);

							serviceWorker.updateCountry(country, code);
							break;
						}
					}
					break;

				}

			}
			break;
		}
		
	}

}
