package country.app;

import country.service.IServiceWorker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;


@SuppressWarnings("all")
public class App {
	public static void main(String[] args) {
		ApplicationContext applicationContext =
				new ClassPathXmlApplicationContext("beans/*.xml");
		IServiceWorker serviceWorker = applicationContext.getBean(IServiceWorker.class);
		while (true) {
			System.out.print("\n");
			System.out.println("Enter a number between 0 and 5");
			System.out.println("0 : Exit");
			System.out.println("1 : Add a new country");
			System.out.println("2 : List information about a country");
			System.out.println("3 : Delete an existing country by code");
			System.out.println("4 : Update information of an existing country by code");
			System.out.println("5 : List all countries of a given continent");
			System.out.print("Code :");
			Scanner inputFromConsole = new Scanner(System.in);
			String code;
			String operation = inputFromConsole.next();
			switch (operation){
				case "0":
					System.exit(0);
					break;
				case "1":
					System.out.print("Enter a country to add in the given format: Code,Name,Currency,Greeting,ContinentCode");
					String input = inputFromConsole.next();
					serviceWorker.saveCountry(input);
				break;

				case "2":
					System.out.print("Enter a country code: ");
					code = inputFromConsole.next();
					serviceWorker.dealWithCountryByCode(code);
					break;

				case "3":
					System.out.print("Enter a country code to delete: ");
					code = inputFromConsole.next();
					serviceWorker.deleteCountry(code);
					break;

				case "4":
					break;
				case "5":
					System.out.print("Enter a continent code to display its countries: ");
					 code = inputFromConsole.next();
					 serviceWorker.dealWithContinentByCode(code);
					break;
				default:
					break;
			}
		}
	}
	
}
