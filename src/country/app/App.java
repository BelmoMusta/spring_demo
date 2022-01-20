package country.app;
/**/
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
		ApplicationContext applicationContext =
				new ClassPathXmlApplicationContext("beans/*.xml");
		IServiceWorker serviceWorker = applicationContext.getBean(IServiceWorker.class);
		int ch = 0;
		while (true) {
			System.out.print("\n ");
			System.out.print("Pour l'ajout d'un nouveau pays tapper 1 ");
			System.out.print("Pour lister les informations d'un pays tapper 2 ");
			System.out.print("Pour supprimer un pays tapper 3 ");
			System.out.print("Pour modifier des informations d'un pays tapper 4 ");
			System.out.print("Pour lister tous les pays d'un continent tapper 5 ");
            System.out.print("Pour sortir de l'application tapper 0 ");
			System.out.print("choisir un nombre de 0 ра 5 ");

			Scanner inputFromConsole = new Scanner(System.in);
			ch = inputFromConsole.nextInt();

			switch (ch){
			
			case 1:
				//add a country
				System.out.print("ajouter le pays sous le format suivant: code,name,devise,greetings ");
				String country = inputFromConsole.next();
				serviceWorker.addCountry(country);
				break;
			case 2:
				//select a country
				System.out.print("saisir le code pays: ");
				String codeCountry = inputFromConsole.next();
				serviceWorker.dealWithCountryByCode(codeCountry);
				break;
			case 3:
				//delete a country
				System.out.print("saisir le code pays р supprimer: ");
				codeCountry = inputFromConsole.next();
				serviceWorker.deleteCountryByCode(codeCountry);
				break;
			case 4:
				System.out.print("saisir le code pays р modifier: ");
				codeCountry = inputFromConsole.next();
				serviceWorker.modifyCountryByCode(codeCountry, null);
				break;
			case 5:
				//Select countries of a continent
				System.out.print("saisir le code du continent pour lister tout les pays:");
				String codeContinent = inputFromConsole.next();
				serviceWorker.getContinentCountriesByCode(codeContinent);
				break;
				
			case 0:
				//exit
				System.exit(0);
				break;

		}
			
		}
	}
	
}
