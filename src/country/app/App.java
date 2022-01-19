package country.app;

import country.model.Country;
import country.service.IServiceWorker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
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
		
		
		System.out.println("1 -->Ajouter un pays");
		System.out.println("2 -->Lister les informations d'un pays");
		System.out.println("3 -->Supprimer un pays");
		System.out.println("4 -->Modifier les informations d'un pays");
		System.out.println("5 -->Lister les pays d'un continent");
		System.out.println("0 -->Sortir");
		
		org.h2.tools.Server.createWebServer().start();
		while (true) {
			System.out.println("Votre choix:");
			String choice = inputFromConsole.next();
			
			switch (choice) {
			//
			case "1": {
				Country country=new Country();
				
				System.out.println("Ajouter les informations du pays comme: "
						+ "code,name,devise,greeting,nameofcontinent:");
				String input=inputFromConsole.next();
				
				try{
					String[] informationOfCountry=input.split(",");
					
					country.setCode(informationOfCountry[0]);
					country.setName(informationOfCountry[1]);
					country.setDevise(informationOfCountry[2]);
					country.setGreetings(informationOfCountry[3]);
					serviceWorker.InsertNewCountry(country,informationOfCountry[4]);
					
				}
				catch (ArrayIndexOutOfBoundsException e) {
					System.err.println("Remarque: Les données doivent être fournie comme suvant"
							+ " code,name,devise,greeting,nameofcontinent \n");
				}
				
				
			}
				      break;
			
		}
	}
	
}
}
