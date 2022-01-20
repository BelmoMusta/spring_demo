package country.app;

import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import country.config.HBconfiguration;
import country.model.Country;
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
		String codePays = "", ModifierInfos = "", CodeContinent = "";
		Country ctr = null;
		while (true) {

			System.out.print("entrer le numéro :");

			inputFromConsole = new Scanner(System.in);
			String number_typed = inputFromConsole.next();
			switch (Integer.parseInt(number_typed)) {
			case 0:

				System.out.println("exit...");
				System.exit(0);

				break;
			case 1:
				System.out.print(
						"entrer les informations ,form de la saisie : code,Nom, devise, greetings,codeContinent:\n");
				inputFromConsole = new Scanner(System.in);
				String countryInfos = inputFromConsole.next();
				serviceWorker.addNewCountry(countryInfos);
				break;

			case 2:

				// serviceWorker.dealWithCountryByCode(codePays);

				while (hashCode(ctr)) {

					System.out.print("entrer le code correct du pays:");
					inputFromConsole = new Scanner(System.in);
					codePays = inputFromConsole.next();
					System.out.print("*********************DEBUT : Pays informations********************\n");
					ctr = serviceWorker.dealWithCountryByCode(codePays);
					System.out.print("*********************FIN   : Pays informations********************\n");
				}

				ctr = null;
				break;
			case 3:
				System.out.println("Entrer le code du pays à supprimer:");
				inputFromConsole = new Scanner(System.in);
				codePays = inputFromConsole.next();
				serviceWorker.DeleteCountryByCode(codePays);
				codePays = "";
				break;
			case 4:
				while (hashCode(ctr)) {

					System.out.println("entrer le code correct du pays à modifier:");
					inputFromConsole = new Scanner(System.in);
					codePays = inputFromConsole.next();
					System.out.print("*********************DEBUT : les informations à modifier********************\n");
					ctr = serviceWorker.dealWithCountryByCode(codePays);
					System.out
							.println("*********************FIN   : les informations à modifier********************\n");
				}
				ctr = null;
				System.out.println("*********************************************************************");
				System.out.println(
						"Entrer les informatioms nécessaires pour la modification sous la forme \" Nom, devise, greetings\":");

				inputFromConsole = new Scanner(System.in);
				ModifierInfos = inputFromConsole.next();
				serviceWorker.EditCountryByCode(codePays, ModifierInfos);
				codePays = "";
				break;
			default:
				break;
			case 5:
				System.out.println("Entrer le code de la continent:");
				inputFromConsole = new Scanner(System.in);
				CodeContinent = inputFromConsole.next();
				serviceWorker.CountriesOfSameContinent(CodeContinent);
				CodeContinent = "";
				break;

			}
		}

	}

	public static Boolean hashCode(Object o) {
		return o != null ? false : true;
	}
}
