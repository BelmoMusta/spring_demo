package country.app;

import country.model.Country;
import country.service.IServiceWorker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import configuration.HibernateConfiguration;

import java.util.Scanner;

@SuppressWarnings("all")
public class App {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(HibernateConfiguration.class);
		IServiceWorker serviceWorker = applicationContext.getBean(IServiceWorker.class);
		int choix=0;
		while (true) {
			System.out.println("\nLe menu devra etre interactif : \r\n" + 
					"1- Pour l'ajout d'un nouveau pays tapper 1,r\n" + 
					"2- Pour lister les informations d'un pays, tapper 2, (le code pays sera saisi par l'utilisateur).\r\n" + 
					"3- Pour supprimer un pays, tapper 3, (le code pays sera saisi par l'utilisateur).\r\n" + 
					"4- Pour modifier des informations d'un pays, tapper 4, (le code pays sera saisi par l'utilisateur).\r\n" + 
					"5- Pour lister tous les pays d'un continent, tapper 5, (le code du continent sera saisi par l'utilisateur).\r\n" + 
					"6- Pour sortir de l'application tapper 0;");
			System.out.println("S'il vous plait entrer un nombre");
			
			Scanner inputFromConsole = new Scanner(System.in);
			choix= inputFromConsole.nextInt();
			if(choix==0)
			{

				System.out.print("Vous étes sorti de l'application");
				System.exit(0);
			}
			else if(choix==1)
			{
				System.out.println("Ajouter un pays comme cet exemple (FR,france,EURO,Bonjour!) : ");
				Scanner inputFromConsole1 = new Scanner(System.in);
				String pays = inputFromConsole1.next();
				serviceWorker.InsertCountry(pays);
			}
			else if(choix==2)
			{
				System.out.println("S'il vous plait tapper votre code : ");
				Scanner inputFromConsole1 = new Scanner(System.in);
				String code = inputFromConsole1.next();
				Country country=serviceWorker.findByCode(code);
				System.out.print(" Name: " + country.getName());
				System.out.print(" ,Devise: " + country.getDevise());
				System.out.print(" ,Greetings: " + country.getGreetings());
				System.out.println(",Code: " + country.getCode());
			}
			else if(choix==3)
			{
					System.out.println("S'il vous plait tapper votre code : ");
					Scanner inputFromConsole1 = new Scanner(System.in);
					String code = inputFromConsole1.next();
					serviceWorker.deleteByCode(code);
			}
			else if(choix==4)
			{
				System.out.println("S'il vous plait tapper votre code : ");
				Scanner inputFromConsole1 = new Scanner(System.in);
				String code = inputFromConsole1.next();
				System.out.println("Tapper Nouveau Nom: ");
				Scanner nom = new Scanner(System.in);
				String nnom = inputFromConsole1.next();
				System.out.println("Tapper Nouveau devise: ");
				Scanner devise = new Scanner(System.in);
				String ndevise = inputFromConsole1.next();
				System.out.println("Tapper Nouveau greetings: ");
				Scanner greetings = new Scanner(System.in);
				String ngreetings = inputFromConsole1.next();
				Country country=new Country();
				country.setName(nnom);
				country.setDevise(ndevise);
				country.setGreetings(ngreetings);
				serviceWorker.updateByCode(code,country);
			}
			else if(choix==5)
			{
				System.out.println("entrer le code de contienent pays  : ");
				Scanner inputFromConsole1 = new Scanner(System.in);
				String codecontinent = inputFromConsole1.next();
				serviceWorker.findByCodeContinent(codecontinent);
			}
			else
			{
				System.out.print("ce choix n'existe pas maintenant ");
			}

		}
	}
	
}