package country.app;

import country.model.Country;
import country.service.IServiceWorker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.util.Scanner;

@SuppressWarnings("all")
public class App {
	public static void main(String[] args) throws SQLException {

		ApplicationContext applicationContext =
				new AnnotationConfigApplicationContext("country");
		IServiceWorker serviceWorker = applicationContext.getBean(IServiceWorker.class);
		Scanner inputFromConsole = new Scanner(System.in);
		org.h2.tools.Server.createWebServer().start();
		while (true) {
			System.out.println(" ############ Add a country => 1 #############");
			System.out.println(" ############# lister les informations d'un pays => 2 #############");
			System.out.println(" ############# supprimer un pays => 3 #############");
			System.out.println(" ############# modifier des informations d'un pays => 4 ############# ");
			System.out.println(" ############# lister tous les pays d'un continen => 5 ############# ");
			System.out.println(" ############# sortir  => 0 #############");
			System.out.println(" "
					+ " Entrez votre choix :           ");
		    String query = inputFromConsole.next();

		    switch (query) {
			//
			case "1": {
				Country country=new Country();
				System.out.println("add information of a country like: code,name,device,greeting,nameofcontinent:");
				String input=inputFromConsole.next();
				try{
					String[] informationOfCountry=input.split(",");
					country.setCode(informationOfCountry[0]);
					country.setName(informationOfCountry[1]);
					country.setDevise(informationOfCountry[2]);
					country.setGreetings(informationOfCountry[3]);
					serviceWorker.ajouterCountry(country,informationOfCountry[4]);
				}
				catch (ArrayIndexOutOfBoundsException e) {
					System.err.println("Verify the form of your input");
				}
				
				
			}
			case "2": {
				System.out.println("Enter Code of country: ");
				String input=inputFromConsole.next();
				serviceWorker.afficheCountry(input);
			}
				      break;
			case "3": {
				System.out.println("Enter Code of country: ");
				String input=inputFromConsole.next();
				serviceWorker.SuppCountry(input);
			}
			break;
			case "4": {
				System.out.println("Enter Code of country: ");
				String inputCode=inputFromConsole.next();
				serviceWorker.afficheCountry(inputCode);
				System.out.println("Update un pays, entez les nouveaux informations: code,name,device,greeting,nameofcontinent:");
				String input=inputFromConsole.next();
				try {
				String[] informationOfCountry=input.split(",");
				Country country=new Country();
				country.setCode(informationOfCountry[0]);
				country.setName(informationOfCountry[1]);
				country.setDevise(informationOfCountry[2]);
				country.setGreetings(informationOfCountry[3]);
				serviceWorker.ModifyCountry(country,inputCode,informationOfCountry[4]);
				}catch (ArrayIndexOutOfBoundsException e) {
					System.err.println("Verify the form of your input");
				}
				}
			  break;
			default:
				System.err.println("Unexpected value: " + query);
			}

	}

}
}
