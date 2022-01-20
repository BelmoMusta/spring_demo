package country.app;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import country.configuration.HibernateConfiguration;
import country.service.IServiceWorker;

@SuppressWarnings("all")
public class App {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(HibernateConfiguration.class);
		IServiceWorker serviceWorker = applicationContext.getBean(IServiceWorker.class);
		String UserInput = "";
		while (true) {
			System.out.print("Enter the number of the operation ! ");
			System.out.print("\n***********************************");
			System.out.print("\n-1 Insert a new country .");
			System.out.print("\n-2 Get a country details .");
			System.out.print("\n-3 Delete a country .");
			System.out.print("\n-4 Update a country .");
			System.out.print("\n-5 Get a list of countries by continent  .");
			System.out.print("\n-6 Get the list of the contries .");
			System.out.print("\n-0 to exit . ");
			System.out.print("\nCode : ");
			Scanner inputFromConsole = new Scanner(System.in);
			String language = inputFromConsole.next();
			switch (language) {

				case "1": {
					serviceWorker.AddCountry();
				}
					break;
				case "2": {
					System.out.print("Choisir une langue Ex:(fr) : ");
					Scanner inputFromConsoleCode = new Scanner(System.in);
					String lang = inputFromConsoleCode.next();
					serviceWorker.dealWithCountryByCode(lang);
				}
					break;
				case "3": {
					serviceWorker.DeleteCountry();
				}
					break;
				case "4": {
					serviceWorker.UpdateCountry();
				}
					break;
				case "5": {
					serviceWorker.GetCountriesByContinent();
				}
					break;
				case "6": {
					serviceWorker.GetList();
				}
					break;
				case "0": {
					System.out.println("End of Operation ");
					return;
				}

				default:
					System.out.println("Operation doesn't exist , Try again .");
			}

		}
	}

}
