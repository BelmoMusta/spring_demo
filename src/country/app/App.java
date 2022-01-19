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
		Scanner dataFromConsole = new Scanner(System.in);
		
		
		System.out.println("1 -->Ajouter un pays");
		System.out.println("2 -->Lister les informations d'un pays");
		System.out.println("3 -->Supprimer un pays");
		System.out.println("4 -->Modifier les informations d'un pays");
		System.out.println("5 -->Lister les pays d'un continent");
		System.out.println("0 -->Sortir");
		
		org.h2.tools.Server.createWebServer().start();
		while (true) {
			System.out.println("Votre choix:");
			String choice = dataFromConsole.next();
			
			switch (choice) {
			case "1": {
				Country country=new Country();
				
				System.out.println("Ajouter les informations du pays comme: "
						+ "code,nom,devise,salutation,nomDeContinent:");
				String data=dataFromConsole.next();
				
				try{
					String[] dataArray=data.split(",");
					
					country.setCode(dataArray[0]);
					country.setName(dataArray[1]);
					country.setDevise(dataArray[2]);
					country.setGreetings(dataArray[3]);
					
					serviceWorker.InsertNewCountry(country,dataArray[4]);
					
				}
				catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("Remarque: Les données doivent être fournie comme suvant :"
							+ " code,nom,devise,salutation,nomDeContinent \n");
				}
				
				
			}break;
			
			case "2": {
				System.out.println("Saisissez le code du pays: ");
				String code=dataFromConsole.next();
				
				serviceWorker.ListCountryData(code);
			}break;
			
			case "3": {
				System.out.println("Saisissez le code du pays à supprimer: ");
				String code=dataFromConsole.next();
				
				serviceWorker.deleteCountryByCode(code);
			}break;
			
			case "4": {
				
				System.out.println("Saisissez le code du pays à modifier: ");
				String code=dataFromConsole.next();
				serviceWorker.ListCountryData(code);
				
				System.out.println("Entrez les nouvelles informations du pays comme: "
						+ "code,nom,devise,salutation,nomDeContinent:");
				
				String data=dataFromConsole.next();
				try {
				String[] dataArray=data.split(",");
				Country country=new Country();
				country.setCode(dataArray[0]);
				country.setName(dataArray[1]);
				country.setDevise(dataArray[2]);
				country.setGreetings(dataArray[3]);
				
				serviceWorker.updateCountry(code, country, dataArray[4]);
				}catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("Remarque: Les données doivent être fournie comme suvant :"
							+ " code,nom,devise,salutation,nomDeContinent \n");
				}
				}break;
				
			case "5": {
				
				System.out.println("Saisissez le code du continent: ");
				String continentCode=dataFromConsole.next();
				
				serviceWorker.listCountriesByContinent(continentCode);
			}break;
			
		}
	}
	
}
}
