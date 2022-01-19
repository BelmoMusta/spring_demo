package country.app;

import country.service.IServiceWorker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

@SuppressWarnings("all")
public class App {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans/*.xml");
		IServiceWorker serviceWorker = applicationContext.getBean(IServiceWorker.class);
		boolean exit = false;
		while (!exit) {
			System.out.println("Menu:************");
			System.out.print("\n" +
					"\t1- Pour l'ajout d'un nouveau pays tapper 1, exemple : FR,france,EURO,Bonjour!\n" +
					"\t2- Pour lister les informations d'un pays, tapper 2.\n" +
					"\t3- Pour supprimer un pays, tapper 3.\n" +
					"\t4- Pour modifier des informations d'un pays, tapper 4.\n" +
					"\t5- Pour lister tous les pays d'un continent, tapper 5.\n" +
					"\t6- Pour sortir de l'application tapper 0.\n");
			System.out.print("Entrer un numero:");
			Scanner inputFromConsole = new Scanner(System.in);
			String number = inputFromConsole.next();
			String code = null;
			switch (number) {
				case "1":
					System.out.print("entrer les informations du pays:");
					code = inputFromConsole.next();
					serviceWorker.addCountry(code);
					break;
				case "2":
					System.out.print("entrer le code du pays:");
					code = inputFromConsole.next();
					serviceWorker.dealWithCountryByCode(code);
					break;
				case "3":
					System.out.print("entrer le code du pays:");
					code = inputFromConsole.next();
					serviceWorker.removeCountry(code);
					break;
				case "4":
					System.out.print("entrer le code du pays:");
					code = inputFromConsole.next();
					System.out.print("entrer les informations du pays:");
					String countryData = inputFromConsole.next();
					serviceWorker.updateCountry(code, countryData);
					break;
				case "5":
					System.out.print("entrer le code du continent:");
					code = inputFromConsole.next();
					serviceWorker.getAllCountriesInContinent(code);
					break;
				case "0":
					System.out.print("au revoir 👋👋👋 !!!");
					exit = !exit;
					break;
				default:
					System.out.println("choisir un numero entre 1 et 6!");
					break;
			}
		}
	}
}
