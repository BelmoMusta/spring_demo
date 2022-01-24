package country.app;

import country.service.IServiceWorker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.util.Scanner;

@SuppressWarnings("all")
public class App {
	public static void main(String[] args) throws SQLException {
		ApplicationContext applicationContext =
				new ClassPathXmlApplicationContext("beans/*.xml");
		IServiceWorker serviceWorker = applicationContext.getBean(IServiceWorker.class);
		org.h2.tools.Server.createWebServer().start();
		while (true) {
			System.out.println("***************************************");
			System.out.println("Si vous voulez quitter l'application  tappez 0");
			System.out.println("***************************************");
			System.out.println("Si vous voulez ajouter un nouveau pays tappez 1");
			System.out.println("***************************************");
			System.out.println("Si vous voulez afficher les informations d'un pays Tappez 2");
			System.out.println("***************************************");
			System.out.println("Si vous voulez supprimer un pays tappez 3");
			System.out.println("***************************************");
			System.out.println("Si vous voulez modifier un pays tappez 4.");
			System.out.println("***************************************");
			System.out.println("Si vous voulez lister les pays d'un continent tappez 5.");
			System.out.println("***************************************");
			Scanner inputFromConsole1 = new Scanner(System.in);
			int code = Integer.parseInt(inputFromConsole1.next());
			switch (code) {
				case 0: {
					System.exit(0);
				}
				case 1: {
					System.out.print("Saisir le pays à ajouter : ");
					Scanner inputFromConsole = new Scanner(System.in);
					String language = inputFromConsole.next();
					serviceWorker.ajouterPays(language);
					break;
				}
				case 2: {
					System.out.print("Saisir le code du pays : ");
					Scanner inputFromConsole = new Scanner(System.in);
					String language = inputFromConsole.next();
					serviceWorker.getCountryData(language);
					break;
				}
				case 3: {
					System.out.print("Saisir le code du pays à supprimer: ");
					Scanner inputFromConsole = new Scanner(System.in);
					String countryCode = inputFromConsole.next();
					serviceWorker.supprimerPays(countryCode);
					break;
				}
				case 4: {
					System.out.print("Saisir le code du pays à modifier: ");
					Scanner inputFromConsole = new Scanner(System.in);
					String language = inputFromConsole.next();
					System.out.print("Veuillez saisir les nouveaux champs 'code,name,devise,greetings,code du continent' ");
					Scanner inputFromConsole2 = new Scanner(System.in);
					String modif = inputFromConsole.next();
					try{
						serviceWorker.modifierPays(language,modif);

					}catch (IndexOutOfBoundsException e){
						System.err.print("Le code du pays entré n'existe pas \n");
					}
					break;
				}
				case 5: {
					System.out.print("Veuillez saisir le code du continent à lister: ");
					Scanner inputFromConsole = new Scanner(System.in);
					String continentCode = inputFromConsole.next();
					serviceWorker.getCountriesByContinent(continentCode);
					break;
				}

				default: {
					System.out.print("Veuillez saisir un code entre 0 et 5. \n");
				}

			}

		}
	}
}
