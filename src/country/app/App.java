package country.app;

import country.service.IServiceWorker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.util.Scanner;

@SuppressWarnings("all")
public class App {
	public static void main(String[] args) throws SQLException {
		ApplicationContext applicationContext =
				//new ClassPathXmlApplicationContext("beans/*.xml");
				new AnnotationConfigApplicationContext("appConfig");
		IServiceWorker serviceWorker = applicationContext.getBean(IServiceWorker.class);

		org.h2.tools.Server.createWebServer().start();

		while (true) {

			System.out.print("*******************************");
			System.out.print(" \n 0 : exit \n 1 : add country \n 2 :  get country data \n 3 : delete coutry \n 4 : update country \n 5 : get countries. \n");
			System.out.print("******************************* \n");

			System.out.print("Enter a code from 0 to 5 : ");
			Scanner inputFromConsole1 = new Scanner(System.in);
			int num = Integer.parseInt(inputFromConsole1.next());

			switch (num) {

				case 0: {
					System.exit(0);
				}

				case 1: {

					System.out.print("Enter the Country Data (code,name,Currency,greetings,continent_code):");
					Scanner inputFromConsole = new Scanner(System.in);
					String language = inputFromConsole.next();
					serviceWorker.addNewCountry(language);
					break;
				}

				case 2: {

					System.out.print("Enter the country code : ");
					Scanner inputFromConsole = new Scanner(System.in);
					String language = inputFromConsole.next();
					serviceWorker.getCountryData(language);
					break;
				}
				case 3: {

					System.out.print("Enter the code to delete a country: ");
					Scanner inputFromConsole = new Scanner(System.in);
					String language = inputFromConsole.next();
					serviceWorker.deletCountry(language);
					break;
				}

				case 4: {
					System.out.print("Enter the code to edit a country: ");

					Scanner inputFromConsole = new Scanner(System.in);
					String language = inputFromConsole.next();
					System.out.print("Enter the new field (code,name,currency,greetings,continent_code) ");
					Scanner inputFromConsole2 = new Scanner(System.in);
					String update = inputFromConsole.next();
					serviceWorker.updateCountry(language, update);
					break;

				}
				case 5: {
					System.out.print("Enter the code of the continent: ");
					Scanner scanner1 = new Scanner(System.in);
					String language = scanner1.next();
					serviceWorker.getCountries(language);
					break;
				}

			}
		}

	}
}