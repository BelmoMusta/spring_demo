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
			System.out.print("Entrer le code : ");
			System.out.println("\n1 - Pour ajouter un nouveau pays :");
			Scanner inputFromConsole1 = new Scanner(System.in);
			int code = Integer.parseInt(inputFromConsole1.next());
			switch (code) {
				case 0: {
					System.exit(0);
				}
				case 1: {
					System.out.print("\nEntrer Un pays sous-forme 'Le code de pays, Le nom de pays, son devise, son salut, son continent' :\n");
					Scanner inputFromConsole = new Scanner(System.in);
					String language = inputFromConsole.next();
					serviceWorker.addNewCountry(language);
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