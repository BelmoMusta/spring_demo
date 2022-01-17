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
			try {
				Thread.sleep(2000);
			}catch (Exception E){}

			System.out.println("Entrer le code : ");
			System.out.println("1 - Pour ajouter un nouveau pays :");
			System.out.println("2 - Pour lister les informations d'un pays :");
			System.out.println("3 - Pour supprimer un pays :");
			System.out.println("4 - Pour modifier les informations d'un pays :");
			System.out.println("5 - Pour lister tous les pays d'un continent :");




			Scanner inputFromConsole1 = new Scanner(System.in);
			int code = Integer.parseInt(inputFromConsole1.next());
			switch (code) {

				case 1: {
					System.out.println("Entrer Un pays sous-forme 'Le code de pays, Le nom de pays, son devise, son salut, son continent' :\n");
					Scanner inputFromConsole = new Scanner(System.in);
					String language = inputFromConsole.next();
					serviceWorker.addNewCountry(language);
					break;
				}
				case 2: {
					System.out.print("Entrer le code du pays : ");
					Scanner inputFromConsole = new Scanner(System.in);
					String language = inputFromConsole.next();
					serviceWorker.getCountryInformations(language);
					break;
				}
				case 3: {
					System.out.println("Entrer le code du pays à supprimer: ");
					Scanner inputFromConsole = new Scanner(System.in);
					String language = inputFromConsole.next();
					serviceWorker.deleteCountry(language);
					break;
				}
				case 4: {
					System.out.print("Entrer le code du pays à modifier: ");
					Scanner inputFromConsole = new Scanner(System.in);
					String language = inputFromConsole.next();
					System.out.print("Entrer les nouveaux champs sous la forme suivante 'Le code de pays, Le nom de pays, son devise, son salut, son continent' :\n ");
					Scanner inputFromConsole2 = new Scanner(System.in);
					String modif = inputFromConsole.next();
					serviceWorker.updateCountry(language,modif);
					break;
				}
				case 5: {
					serviceWorker.getCountries();
					break;
				}
				default:{
					System.out.print("Choisir une langue : ");
					Scanner inputFromConsole = new Scanner(System.in);
					String language = inputFromConsole.next();
					serviceWorker.dealWithCountryByCode(language);
					break;
				}
			}
		}
	}

}