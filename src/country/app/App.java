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
			System.out.print(
					"\n\nPour l'ajout d'un nouveau pays tapper 1.\n"
					+ "Pour lister les informations d'un pays, tapper 2.\n"
					+ "Pour supprimer un pays, tapper 3 : \n"
					+ "Pour modifier des informations d'un pays, tapper 4.\n"
					+ "Pour lister tous les pays d'un continent, tapper 5.\n"
					+ "Pour sortir de l'application tapper 0.\n");
			System.out.print("Taper votre choix s'il vous plait : ");
			Scanner inputFromConsole = new Scanner(System.in);
			int choix = inputFromConsole.nextInt();
			
			if(choix ==1 ) {
				System.out.print("Ajouter les informations du pays : ");
				inputFromConsole = new Scanner(System.in);
				String coutryInfo = inputFromConsole.next();
				serviceWorker.addNewCountry(coutryInfo);
			}
			else if (choix == 2 ) {
				System.out.print("Entrer le code Pour lister les informations du pays : ");
				inputFromConsole = new Scanner(System.in);
				String code = inputFromConsole.next();
				serviceWorker.dealWithCountryByCode(code);
			}
			else if(choix == 3) {
				System.out.print("Entrer le code pour supprimer le pays : ");
				inputFromConsole = new Scanner(System.in);
				String code = inputFromConsole.next();
				serviceWorker.deleteCountryByCode(code);
			}
			else if(choix == 4) {
				System.out.print("Entrer le code et les nouveaux inforations du pays pour modifier ses informations s'il vous plais : ");
				inputFromConsole = new Scanner(System.in);
				String code = inputFromConsole.next();
				String newInfo=inputFromConsole.next();
				serviceWorker.ModifCountryByCode(code,newInfo);
			}
			else if(choix == 5) {
				System.out.print("Entrer le code du continent pour lister tous ses pays : ");
				inputFromConsole = new Scanner(System.in);
				String continent = inputFromConsole.next();
				System.out.print(serviceWorker.continentCountriesByCode(continent));
			}
			else if(choix == 0) {
				System.exit(choix);
			} else {
				System.out.print("Entrer un choix correct s'il vous plait  ");
				
			}
			
		}
	}
	
}
