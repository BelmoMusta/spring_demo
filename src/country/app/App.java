package country.app;

import country.model.Country;
import country.service.IServiceWorker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.NoSuchElementException;
import java.util.Scanner;

@SuppressWarnings("all")
public class App {
	public static void main(String[] args) {

		ApplicationContext applicationContext = new AnnotationConfigApplicationContext("country");
		IServiceWorker serviceWorker = applicationContext.getBean(IServiceWorker.class);
		try{
			Scanner inputFromConsole = new Scanner(System.in);
		while (true) {
			System.out.println("Add a country------------------------->1");
			System.out.println("Select a country---------------------->2");
			System.out.println("Delete a country---------------------->3");
			System.out.println("Update a country---------------------->4");
			System.out.println("Select countries of a continent------->5");
			System.out.println("Exit---------------------------------->0");
			String query = inputFromConsole.next();
			switch (query) {

			case "1": {
				Country country = new Country();
				System.out.println("add information of a country like: code,name,device,greeting,nameofcontinent:");
				String input = inputFromConsole.next();
				try {
					String[] informationOfCountry = input.split(",");
					country.setCode(informationOfCountry[0]);
					country.setName(informationOfCountry[1]);
					country.setDevise(informationOfCountry[2]);
					country.setGreetings(informationOfCountry[3]);
					serviceWorker.InsertCountry(country, informationOfCountry[4]);
				} catch (ArrayIndexOutOfBoundsException e) {
					System.err.println("Verify the form of your input");
				}

			}
				break;
			case "2": {
				System.out.println("Enter Code of country: ");
				String input = inputFromConsole.next();
				serviceWorker.selectCountry(input);
			}
				break;
			case "3": {
				System.out.println("Enter Code of country: ");
				String input = inputFromConsole.next();
				serviceWorker.deleteCountry(input);
			}
				break;
			case "4": {
				System.out.println("Enter Code of country: ");
				String code = inputFromConsole.next();
				if (serviceWorker.isExist(code)) {
					System.out.println(
							"Enter new information of the country like: code,name,device,greeting,nameofcontinent:");
					String input = inputFromConsole.next();
					try {
						String[] informationOfCountry = input.split(",");
						Country country = new Country();
						country.setCode(informationOfCountry[0]);
						country.setName(informationOfCountry[1]);
						country.setDevise(informationOfCountry[2]);
						country.setGreetings(informationOfCountry[3]);
						serviceWorker.updateCountry(country, code, informationOfCountry[4]);
					} catch (ArrayIndexOutOfBoundsException e) {
						System.err.println("Verify the form of your input");
					}
				} else
					System.err.println("no country with this code ");
			}
				break;
			case "5": {
				System.out.println("Enter Code of continent: ");
				String input = inputFromConsole.next();
				serviceWorker.selectCountriesOfContinent(input);
			}
				break;
			case "0":
				System.exit(0);
				break;

			default:
				System.err.println("Unexpected value: " + query);
			}

		}
		}catch (NoSuchElementException e) {
			System.out.println("End");
		}
	};
}
