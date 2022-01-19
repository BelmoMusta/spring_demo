package country.app;

import country.service.IServiceWorker;
import country.service.impl.ServiceWorkerImpl;
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
			System.out.println(
					"1-Pour ajouter un  pays\n"
							+ "2-Pour afficher les informations d'un pays\n"
							+ "3-Pour supprimer un pays\n"
							+ "4-Pour modifier les informations d'un pays\n"
							+ "5-Pour afficher les pays d'un continent\n"
							+ "6-Pour quitter\n"
							+ "Entrez un numéro:");
			Scanner choice = new Scanner(System.in);
			switch(choice.nextInt()) {
				case 1:
					System.out.println("Veullier entrer les informations du pays de la forme suivante :name,code,currency,greetings,code-continent:\n");
					Scanner country_input = new Scanner(System.in);
					String countryInfos = country_input.next();
					serviceWorker.SaveCountry(countryInfos);
					break;
				case 2:
					System.out.println("Veullier entrer le code du pays à afficher:");
					Scanner countryToFindInput = new Scanner(System.in);
					String countryToFind = countryToFindInput.next();
					serviceWorker.dealWithCountryByCode(countryToFind);
					break;
				case 3:
					System.out.println("Veullier entrer le code du pays à afficher:");
					Scanner countryToDeleteInput = new Scanner(System.in);
					String countryToDelete = countryToDeleteInput.next();
					serviceWorker.DeleteCountry(countryToDelete);
					break;
				case 0:
					System.out.println("Au revoir");
					System.exit(0);
					break;
				default:
					System.out.println("Entrez un numéro");
			}
			
		}
	}
	
}
