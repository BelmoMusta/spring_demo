package country.app;

import country.dao.CountryDAOImpl;
import country.model.Country;
import country.service.IServiceWorker;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import configuration.AppConfig;

import java.io.File;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("all")
public class App {
	
	public static void main(String[] args) throws HibernateException {
		
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		IServiceWorker serviceWorker = applicationContext.getBean(IServiceWorker.class);

		
		Scanner inputFromConsole = new Scanner(System.in);
		
		while(true) {
			//Help user
			System.out.println("Pour ajouter des informations sur un pays, clicker sur ---------> 1");
			System.out.println("Pour afficher des informations sur un pays, clicker sur --------> 2");
			System.out.println("Pour supprimer un pays, clicker sur ----------------------------> 3");
			System.out.println("Pour modifié les informations d'un pays, clicker sur -----------> 4");
			System.out.println("Pour afficher des informations sur un continent, clicker sur ---> 5");
			System.out.println("Pour sortir de l'application, clicker sur ----------------------> 0");
			//Input user
			System.out.println("Enter numero :");
			String chose = inputFromConsole.next();
			
			switch(chose) {
				
				//Aspect fonctionnel 1	
				case "1" :{
					Country country=new Country();
					System.out.println("Entrer les informations sous forme : code,name,device,greeting,continent_name\n"
							+ "Pour le nom du continent : Europe, Afrique, Asie, Amerique, Australie\n"
							+ "Par exemple : ma,Maroc,MAD,Salam,Afrique\n");
					String informations = inputFromConsole.next();
					
					try{
						String[] infos = informations.split(",");
						country.setCode(infos[0]);
						country.setName(infos[1]);
						country.setDevise(infos[2]);
						country.setGreetings(infos[3]);
						
						serviceWorker.dealWithAddCountry(country,infos[4]);
					}catch (Exception e) {
						System.err.println("Entrer les informations sous forme : code,name,device,greeting,contnent_name !");
					}
					break;
				}
				//Aspect fonctionnel 2	
				case "2" :{
					System.out.println("Entrer le code : ");
					String language = inputFromConsole.next();
					serviceWorker.dealWithCountryByCode(language);
					break;
				}
				//Aspect fonctionnel 3	
				case "3" :
					System.out.println("Entrer le code : ");
					String code = inputFromConsole.next();
					serviceWorker.dealWithDeleteCountry(code);
					break;
				
				//Aspect fonctionnel 4	
				case "4" :{
					System.out.println("Enter le code du pays que vous voulez modifié: ");
					String codeInput = inputFromConsole.next();
					
					System.out.println("Entrer les informations sous forme : code,name,device,greeting,continent_name\n"
							+ "Pour le nom du continent : Europe, Afrique, Asie, Amerique, Australie\n"
							+ "Par exemple : ma,Maroc,MAD,Salam,Afrique\n");
					String informations = inputFromConsole.next();
					
					try {
						String[] infos = informations.split(",");
						Country country = new Country();
						country.setCode(infos[0]);
						country.setName(infos[1]);
						country.setDevise(infos[2]);
						country.setGreetings(infos[3]);
						serviceWorker.dealWithUpdateCountry(codeInput,country,infos[4]);
					}catch (Exception e) {
						System.err.println("Entrer les informations sous forme : code,name,device,greeting,contnent_name !");
					}
					
					break;
				}
			
				//Aspect fonctionnel 5	
				case "5" :{ 
					System.out.println("Enter Code of continent: ");
					System.out.println("Europe : eu\n"
							+ "Afrique: af\n"
							+ "Australie : aus\n"
							+ "Amerique : ame\n"
							+ "Asie : asie");
					String input = inputFromConsole.next();
					serviceWorker.dealwWithSelectCountriesOfContinent(input);
					break;
				}
				
				//Aspect fonctionnel 6
				case "0" :{
					
					System.exit(0);
					break;
				}
				
				default :{
					System.err.println("Entrée invalide !");
					break;
				}
			}
		}
	}
}
