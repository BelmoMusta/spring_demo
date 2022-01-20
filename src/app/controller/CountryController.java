package app.controller;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.dao.ContinentDAO;
import app.entity.Continent;
import app.entity.Country;
import app.service.IServiceWorker;

@Component
public class CountryController {
	@Autowired
	private IServiceWorker serviceWorker;
	@Autowired
	private ContinentDAO continentDAO;

	public void exitApp() {
		System.out.println("\n\n");
		System.out.println("=============");
		System.out.println("Bay Bay......");
		System.out.println("=============");
		System.exit(0);
	}

	public void addCountry(Scanner inputFromConsole) {
		System.out.println("Ajouter un nouveau pays (code de pays,nom,devise,salutation,code de continent): ");
		while (true) {
			if (inputFromConsole.hasNext()) {
				String pays = inputFromConsole.next();
				if (pays.equals("0")) {
					return;
				}
				String[] _pays = pays.split(",");
				if (_pays.length != 5) {
					System.err.print("!! le format tappait est inccorect , essayer à nouveau   (0 Menu): ");
					continue;
				} else {
					Country country = serviceWorker.getCountry(_pays[0]);
					Continent continent = continentDAO.getByCode(_pays[4]);
					if (country == null) {
						if (continent != null) {
							country = new Country();
							country.setCode(_pays[0]);
							country.setName(_pays[1]);
							country.setDevise(_pays[2]);
							country.setGreetings(_pays[3]);
							country.setContinent(continent);
							serviceWorker.addCountry(country);
							System.out.println("le pays et bien ajouter . ");
							return;
						} else {
							System.err.print(String.format(
									"Le continent associes a ce code [%s] n'existe pas , essayer à nouveau (0 Menu): ",
									_pays[4]));
							continue;
						}

					} else {
						System.err.println("Ce pays et deja existe....");
						return;
					}
				}

			}
			return;
		}
	}

	public void getCountry(Scanner inputFromConsole) {
		System.out.print("Tappez le code de pays que vous voulez lister ses informations: ");
		while (true) {
			if (inputFromConsole.hasNext()) {
				String code = inputFromConsole.next();
				if (code.equals("0")) {
					return;
				}
				Country country = serviceWorker.getCountry(code);
				if (country == null) {
					System.err.print("Le pays n'existe pas , tappez un autre code correct (0 Menu) : ");
				} else {
					System.out.println(country.toString());
					return;
				}

			}
		}
	}

	public void deleteCountry(Scanner inputFromConsole) {
		System.out.print("Tappez le code de pays que vous voulez supprimer : ");
		while (true) {
			if (inputFromConsole.hasNext()) {
				String code = inputFromConsole.next();
				if (code.equals("0")) {
					return;
				}
				Country country = serviceWorker.getCountry(code);
				if (country == null) {
					System.err.print("Le pays n'existe pas , tappez un autre code correct (0 Menu) : ");
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
				if (code.equals("0")) {
					return;
				}
				Country country = serviceWorker.getCountry(code);
				if (country == null) {
					System.err.print("Le pays n'existe pas , tappez un autre code correct (0 Menu) :");
					continue;
				} else {
					System.out.print(
							String.format("Tappez les nouveaux informations de pays [%s]  (code de pays,nom,devise,salutation,code de continent) : ", country.getName()));
					while (inputFromConsole.hasNext()) {
						String pays = inputFromConsole.next();
						if (pays.equals("0")) {
							break;
						}
						String[] _pays = pays.split(",");
						if (_pays.length != 5) {
							System.err.println("!! le format tappait est inccorect , essayer à nouveau (0 Menu): ");
						} else {
							Continent continent = continentDAO.getByCode(_pays[4]);
							if (continent != null) {
								Country newCountry =  new Country() ;
								newCountry.setId(country.getId());
								newCountry.setCode(_pays[0]);
								newCountry.setName(_pays[1]);
								newCountry.setDevise(_pays[2]);
								newCountry.setGreetings(_pays[3]);
								newCountry.setContinent(continent);
								serviceWorker.updateCountry(newCountry);
								break;
							} else {
								System.err.println(String.format(
										"Le continent associes a ce code [%s] n'existe pas , essayer à nouveau : ",
										_pays[4]));
								continue;
							}

						}
					}
					break;

				}

			}
			break;
		}

	}

	public void getCountriesByContinent(Scanner inputFromConsole) {
		System.out.print("Tappez le code de continent que vous voulez lister ses pays: ");
		while (true) {
			if (inputFromConsole.hasNext()) {
				String code = inputFromConsole.next();
				if (code.equals("0")) {
					return;
				}
				List<Country> countries = serviceWorker.getCountriesByContinent(code);
				if (countries == null || countries.isEmpty()) {
					System.err.print(String.format("Le continet n'existe pas , ou pas de pays enregistrer avec ce continent [%s] , tappez un autre code valide (0 Menu) : ",code));
					continue;
				} else {
					System.out.println(countries.get(0).getContinent().getName());
					countries.forEach(country -> System.out.println(country.toString()));
					return;
				}

			}
		}
	}

}
