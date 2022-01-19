package country.app;

import country.service.IServiceWorker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;
import java.sql.SQLException;

@SuppressWarnings("all")
public class App {
	public static void main(String[] args) throws SQLException {
		ApplicationContext applicationContext =
				new ClassPathXmlApplicationContext("beans/*.xml");
		IServiceWorker serviceWorker = applicationContext.getBean(IServiceWorker.class);
		org.h2.tools.Server.createWebServer().start();

		while (true) {
			System.out.println("\n");
			System.out.println("tapez 1 pour l'ajout d'un nouveau pays");
			System.out.println("tapez 2 Pour lister les informations d'un pays");
			System.out.println("tapez 3 Pour supprimer un pays");
			System.out.println("tapez 4 Pour modifier des informations d'un pays");
			System.out.println("tapez 5 Pour lister tous les pays d'un continent");
			System.out.println("tapez 0 pour sortir ");
			System.out.print("entrer un nombre:");
			Scanner scanner = new Scanner(System.in);
			int choix = Integer.parseInt(scanner.next());
			switch (choix) {
				case 0: {
					System.exit(0);
				}
				case 1: {
					System.out.print("ajouter le pays sous forme code,name,devise,greetings,continent :");
					Scanner scanner1 = new Scanner(System.in);
					String information = scanner1.next();
					serviceWorker.addCountry(information);
					break;
				}
				case 2: {
					System.out.print("Entrer le code du pays : ");
					Scanner scanner1 = new Scanner(System.in);
					String code = scanner1.next();
					serviceWorker.getInfos(code);
					break;
				}
				case 3: {
					System.out.print("Entrer le code du pays à supprimer: ");
					Scanner scanner1 = new Scanner(System.in);
					String code = scanner1.next();
					serviceWorker.deleteCountry(code);
					break;
				}
				case 4: {
					System.out.print("Entrer le code du pays à modifier: ");
					Scanner scanner1 = new Scanner(System.in);
					String code = scanner1.next();
					System.out.print("Entrer les informations sous forme code,name,devise,greetings,continent :");
					Scanner scanner2 = new Scanner(System.in);
					String information = scanner2.next();
					serviceWorker.updateCountry(code,information);
					break;
				}
				case 5: {
					System.out.print("Entrer le code du continent: ");
					Scanner scanner1 = new Scanner(System.in);
					String code = scanner1.next();
					serviceWorker.getCountries(code);
					break;
				}
				default: {
					System.out.print("Entrer un code : ");
					Scanner scanner1 = new Scanner(System.in);
					String code = scanner1.next();
					serviceWorker.dealWithCountryByCode(code);
					break;

				}
			}

		}

	}
}
