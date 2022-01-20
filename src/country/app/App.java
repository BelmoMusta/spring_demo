package country.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import country.model.Country;
import country.service.IServiceWorker;
import java.util.Scanner;
//I changed this for pull request purpose

@EnableTransactionManagement
@SuppressWarnings("all")
public class App {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext("country");
		
		IServiceWorker serviceWorker = applicationContext.getBean(IServiceWorker.class);
		
		while (true) {
			System.out.print("\n");
			System.out.print("1-Ajouter un nouveau pays ");
			System.out.print("2-Lister les informations d'un pays ");
			System.out.print("3-Supprimer un pays ");
			System.out.print("4-Modifier les informations d'un pays ");
			System.out.print("5-Lister les pays d'un continent ");
			System.out.print("0-Sortir de l'application \n");
			
			Scanner inputFromConsole = new Scanner(System.in);
			String code = inputFromConsole.next();
			
			if ("0".equals(code)) {
				System.exit(0);  // exit the program
				
			} else if ("1".equals(code)) {
				Country country=new Country();
				System.out.println("Ajouter les informations d'un pays comme suite : code,name,device,greeting,name_continent:");
				String newcountry = inputFromConsole.next();
				serviceWorker.addCountry(newcountry);
				
			} else if ("2".equals(code)) {
				System.out.println("Entrer le code du pays: ");
				String input=inputFromConsole.next();
				serviceWorker.dealWithCountryByCode(input);
				
			} else if ("3".equals(code)) {
				System.out.println("Entrer le code du pays: ");
				String entree=inputFromConsole.next();
				serviceWorker.deleteCountryByCode(entree);
				
			} else if ("4".equals(code)) {
				System.out.println("Saisir les nouvelles informations d'un pays comme suite : code,name,device,greeting,name_continent:");
				String updatecountry = inputFromConsole.next();
				serviceWorker.updateCountry(updatecountry);
				
			} else if ("5".equals(code)) {
				System.out.print("Saisir le code du continent pour afficher les pays:");
				String codeofContinent = inputFromConsole.next();
				serviceWorker.getContinentCountries(codeofContinent);
				
			}
			else{  System.err.println("Unexpected value: " + code); }
			
		}
	
	}
}
 
