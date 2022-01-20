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
			System.out.println("\nInstructions : ");
			System.out.println("****************");
			System.out.println("Taper 1 Pour ajouter d'un pays;");
			System.out.println("Taper 2 pour lister les informations d'un pays;");
			System.out.println("Taper 3 pour supprimer un pays;");
			System.out.println("Taper 4 pour modifier des informations d'un pays;");
			System.out.println("Taper 5 pour lister tous les pays d'un continent;");
			System.out.println("Taper 0 pour sortir de l'application; \n");
 
            System.out.print("Taper un Numero entre 0 et 5 : ");			
			Scanner inputFromConsole = new Scanner(System.in);
			int num = inputFromConsole.nextInt();
			
			if(num == 1) {
				System.out.print("Taper les informations du pays : ");
				inputFromConsole = new Scanner(System.in);
				String infos = inputFromConsole.next();
				serviceWorker.addCountry(infos);
			}
			if(num == 2) {
				System.out.print("Entrer le code du pays : ");
				inputFromConsole = new Scanner(System.in);
				String code = inputFromConsole.next();
				if(!serviceWorker.checkIfCountryExists(code)) System.out.println("Pays n'existe pas!!");
				else serviceWorker.dealWithCountryByCode(code);
			}
			if(num == 3) {
				System.out.print("Entrer le code du pays : ");
				inputFromConsole = new Scanner(System.in);
				String code = inputFromConsole.next();
				if(!serviceWorker.checkIfCountryExists(code)) System.out.println("Pays n'existe pas!!");
				else serviceWorker.deleteCountry(code);
			}
			if(num == 4) {
				System.out.print("Entrer le code du pays : ");
				inputFromConsole = new Scanner(System.in);
				String code = inputFromConsole.next();
				if(!serviceWorker.checkIfCountryExists(code)) System.out.println("Pays n'existe pas!!");
				else serviceWorker.updateCountry(code);
			}
			if(num == 5) {
				System.out.print("Entrer le code du continent : ");
				inputFromConsole = new Scanner(System.in);
				String continent = inputFromConsole.next();
				if(!serviceWorker.checkIfContinentExists(continent)) System.out.println("Continent non existant!!");
				else serviceWorker.countries(continent);
			}
			if(num == 0) {
				System.out.print("Etes vous sur de quitter l'application (y ou n) : ");
				inputFromConsole = new Scanner(System.in);
				String rep = inputFromConsole.next();
				if(rep.equals("y")) System.exit(0);
			}
		}
	}
	
}
