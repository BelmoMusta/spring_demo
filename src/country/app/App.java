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
			System.out.print("Enter a number between 0 and 5");
			System.out.print("0 : Exit");
			System.out.print("1 : Add a new country");
			System.out.print("2 : List information about a country");
			System.out.print("3 : Delete an existing country by code");
			System.out.print("4 : Update information of an existing country by code");
			System.out.print("5 : List all countries of a given continent");

			Scanner inputFromConsole = new Scanner(System.in);
			String language = inputFromConsole.next();
			switch (language){
				case "0":
					break;
				case "1":
				break;
				case "2":
					break;
				case "3":
					break;
				case "4":
					break;
				case "5":
					break;
				default:
					break;
			}

		}
	}
	
}
