package country.app;


import country.model.Country;
import country.service.IServiceWorker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

@SuppressWarnings("all")
public class App {
	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext("country");
		IServiceWorker serviceWorker = applicationContext.getBean(IServiceWorker.class);
		Scanner inputFromConsole = new Scanner(System.in);
		
		while (true) {
			System.out.println("\n-----------------------------------------------------------------------------------------------");
			System.out.println("1) Si vous voulez ajouter un pays, cliquez sur 1.");
			System.out.println("2) Si vous voulez lister les informations d'un pays, cliquez sur 2.");
			System.out.println("3) Si vous voulez supprimer un pays, cliquez sur 3.");
			System.out.println("4) Si vous voulez modifier les informations d'un pays, cliquez sur 4.");
			System.out.println("5) Si vous voulez lister tous les pays d'un continent, cliquez sur 5.");
			System.out.println("0) Si vous voulez sortir de l'application, cliquez sur 0.");
			System.out.println("-----------------------------------------------------------------------------------------------");
			System.out.print("Entrer un chiffre de 0 à 5:");
			
		    String chiffre = inputFromConsole.next();
		    
		switch (chiffre) {
		
		//sortir de l'application
		case "0": 
			System.out.println("Vous avez quitter l'application !");
			System.exit(0);
			break;
			
		//ajout d'un nouveau pays
		case "1": {
			
			System.out.println("Entrez les informations du pays à ajouter comme suit: Code,Pays,Device,Salutation,Continent");
			
			Country country=new Country();
			String continent=null;
			
			serviceWorker.AjouterCountry(country, continent);
			
		}
			break;
			
		//Lister les informations d'un pays
		case "2": {
			System.out.println("Entrez le code du pays: ");
		
			
			String CodeOfCountry=inputFromConsole.next();
			serviceWorker.dealWithCountryByCode(CodeOfCountry);
			
			
		}
		break;
		
		//suppression d'un pays
		case "3": {
			System.out.println("Entrez le code du pays à supprimer: ");
			String CodeOfCountry=inputFromConsole.next();
			serviceWorker.supprimerCountry(CodeOfCountry);
		}
		break;
		
		//modifier les informations d'un pays
		case "4": {
			System.out.println("Entrez le code du pays que vous voulez modifier: ");
			String CodeOfCountry=inputFromConsole.next();
			serviceWorker.dealWithCountryByCode(CodeOfCountry);
			System.out.println("Entrez les nouvelles informations du pays comme suit: Code,Pays,Device,Salulation,Continent");
			
			Country country=new Country();
			String continent= null;
			
			serviceWorker.modifierCountry(country,CodeOfCountry,continent);
		
			}
		break;
		
		//Lister les pays d'un continent
		case "5": {
			System.out.println("Entrez le code du continent: ");
			String CodeOfContinent=inputFromConsole.next();
			serviceWorker.ListerCountriesOfContinent(CodeOfContinent);
		}
		break;
		
		
	}
	
		}}
}