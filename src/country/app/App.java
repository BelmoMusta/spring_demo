package country.app;

import country.model.Country;
import country.service.IServiceWorker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import beans.ApplicationConfig;

import java.util.Scanner;

@SuppressWarnings("all")
public class App {
	public static void main(String[] args) {
		
		
		
		ApplicationContext applicationContext =
				new ClassPathXmlApplicationContext("beans/*.xml");

		IServiceWorker service = (IServiceWorker) applicationContext.getBean(IServiceWorker.class);

		/*
		 * Create Employee1
		 */
		Country c1 = new Country();
		c1.setCode("FR");
		c1.setName("France");
		c1.setDevise("euro");
		c1.setGreetings("Bonjours");
		
		service.saveCountry(c1);
		
		
		
		
		
		
		
		
		/*ApplicationContext applicationContext =
				new ClassPathXmlApplicationContext("beans/*.xml");
		IServiceWorker serviceWorker = applicationContext.getBean(IServiceWorker.class);
		
			System.out.print("Choisir un nombre (1:ajouter , 2:afficher , 3:supprimer , 4: modifier , 5: payes d'un continent , 6: sortir : )");
			Scanner inputFromConsole1 = new Scanner(System.in);
			int num = inputFromConsole1.nextInt();
			if(num == 1) {
				System.out.print("ajouter un payé : ");
			}
			
			if(num == 2) {
				System.out.print("Choisir une langue : ");
				Scanner inputFromConsole = new Scanner(System.in);
				String language = inputFromConsole.next();
				serviceWorker.dealWithCountryByCode(language);
				
			}
			if(num == 3) {
				System.out.print("Choisir une langue : ");
				Scanner inputFromConsole = new Scanner(System.in);
				String language = inputFromConsole.next();
				serviceWorker.dealWithCountryByCode(language);
				System.out.print("le payé a etes supprimé : ");
				
			}
			
			if(num == 4) {
				System.out.print("Choisir une langue : ");
				Scanner inputFromConsole = new Scanner(System.in);
				String language = inputFromConsole.next();
				serviceWorker.dealWithCountryByCode(language);
				System.out.print("le payé a etes modifié : ");
				
			}
			
			if(num == 5) {
				System.out.print("Choisir un continent : ");
				Scanner inputFromConsole = new Scanner(System.in);
				String language = inputFromConsole.next();
				serviceWorker.dealWithCountryByCode(language);
			
			}
			
			 
			if(num == 6){
					 System.out.print("sortir du programme: ");
				 }*/
			
			
			
		
	}
	
}
