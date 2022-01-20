package country.app;

import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import country.config.HBconfiguration;
import country.service.IServiceWorker;

@SuppressWarnings("all")
public class App {
	public static void main(String[] args) {

		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
				HBconfiguration.class);
		IServiceWorker serviceWorker = applicationContext.getBean(IServiceWorker.class);
		System.out.print(
				"=================================================================================================================\n"
						+ "1 - Pour l'ajout d'un nouveau pays tapper 1,respecter cette forme!!: \"code,Nom,devise,greetings,codeContinent\"\r\n"
						+ "    Important pour (1)!!:éliminer tout les espaces. \r\n"
						+ "2 - Pour lister les informations d'un pays, tapper 2.\r\n"
						+ "3 - Pour supprimer un pays, tapper 3.\r\n"
						+ "4 - Pour modifier des informations d'un pays, tapper 4.\n"
						+ "5 - Pour lister tous les pays d'un continent, tapper 5.\n"
						+ "6 - Pour sortir de l'application tapper 0.\n"

						+ "=================================================================================================================\n");
		Scanner inputFromConsole;
		String codePays = "";
		Boolean keepWorking = true;
		while (true) {

			System.out.print("entrer le numéro :");

			inputFromConsole = new Scanner(System.in);
			String number_typed = inputFromConsole.next();
			switch (Integer.parseInt(number_typed)) {

			case 1:
				System.out.print(
						"entrer les informations ,form de la saisie : code,Nom, devise, greetings,codeContinent:\n");
				inputFromConsole = new Scanner(System.in);
				String countryInfos = inputFromConsole.next();
				serviceWorker.addNewCountry(countryInfos);
				break;

			case 2:
				System.out.print("entrer le code du pays:");
				inputFromConsole = new Scanner(System.in);
				codePays = inputFromConsole.next();
				// serviceWorker.dealWithCountryByCode(codePays);
				serviceWorker.dealWithCountryByCode(codePays);
				codePays = "";
				break;
			case 3:
				System.out.println("Entrer le code du pays à supprimer:");
				inputFromConsole = new Scanner(System.in);
				codePays = inputFromConsole.next();
				serviceWorker.DeleteCountryByCode(codePays);
				codePays = "";
				break;
			default:

				break;

			}
		}

	}
}
