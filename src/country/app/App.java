package country.app;

import country.config.Config;
import country.service.IServiceWorker;

import org.h2.tools.Server;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.util.Scanner;

@SuppressWarnings("all")
public class App {
	public static void main(String[] args){
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
		IServiceWorker serviceWorker = applicationContext.getBean(IServiceWorker.class);
		
		
		
		while (true) {
			/*System.out.print("Choisir une langue : ");
			Scanner inputFromConsole = new Scanner(System.in);
			String language = inputFromConsole.next();
			serviceWorker.dealWithCountryByCode(language);*/
			System.out.print("Taper un numéro : ");
			Scanner inputFromConsole = new Scanner(System.in);
			
			switch(inputFromConsole.nextInt()) {
			
			/*----------Aspect fonctionnel 1-------------*/
			case 1: System.out.println("saisi les informations du pays [format : \"name,code,currency,greetings,continent\"] :");
			        Scanner add = new Scanner(System.in);
			        String informations = add.nextLine();
			        serviceWorker.SaveCountryByInformations(informations);
			        break;
			
			/*----------Aspect fonctionnel 2-------------*/
			case 2: System.out.print("Choisir une langue : ");
			        Scanner show = new Scanner(System.in);			      
			        String language = show.next();	
			        try {
			        	serviceWorker.dealWithCountryByCode(language);
			        }
			        catch(Exception e) {
			        	e.printStackTrace();
			        }
			        
			        break;
			
			/*----------Aspect fonctionnel 3-------------*/        
			case 3: System.out.println("choisir un pays pour le supprimer");
			        Scanner delete = new Scanner(System.in);
			        String code = delete.next();
			        try {
			        	serviceWorker.dealWithDeleteCountry(code);
			        }catch(Exception e) {
			        	e.printStackTrace();
			        }
			        
			        break;
			/*----------Aspect fonctionnel 4-------------*/  
			case 4:
			        System.out.print("Choisir une langue : ");
	                Scanner codeToUpdate = new Scanner(System.in);
	                String lang = codeToUpdate.next();
	                System.out.println("saisi les informations du pays modifié [format : \"name,code,currency,greetings,continent\"] :");
			        Scanner update = new Scanner(System.in);
	                String infos = update.nextLine();
	                serviceWorker.UpdateCountryByInformations(lang, infos);
	                break; 
	                
	        /*----------Aspect fonctionnel 5-------------*/  
			case 5:  System.out.print("Choisir un code de continent : ");
	                 Scanner showCountries = new Scanner(System.in);
	                 String codeContinent = showCountries.next();  
	                 serviceWorker.CountriesOfContinent(codeContinent);
	                 break;
	        /*----------Aspect fonctionnel 6-------------*/         
			case 0: System.out.println("Application fermée");   
	                System.exit(0);
	        
			default : System.out.println("choisir un autre numéro");
			          break;
	}
			
			
			
			
		}
			
		}
	}
	

