package country.app;



import java.util.*;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import country.configurationHibernate.ConfigurationHibernate;
import country.service.IServiceWorker;

@SuppressWarnings("all")
public class App {
		public static void main(String[] args) {
			AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConfigurationHibernate.class);
			IServiceWorker iserviceWorker = applicationContext.getBean(IServiceWorker.class);
			
			
			int choise=0;
			
			System.out.println("Le menu devra etre interactif : \r\n" + 
					"1- Pour l'ajout d'un nouveau pays tapper 1,r\n" + 
					"2- Pour lister les informations d'un pays, tapper 2, (le code pays sera saisi par l'utilisateur).\r\n" + 
					"3- Pour supprimer un pays, tapper 3, (le code pays sera saisi par l'utilisateur).\r\n" + 
					"4- Pour modifier des informations d'un pays, tapper 4, (le code pays sera saisi par l'utilisateur).\r\n" + 
					"5- Pour lister tous les pays d'un continent, tapper 5, (le code du continent sera saisi par l'utilisateur).\r\n" + 
					"6- Pour sortir de l'application tapper 0;");
			while(true) {
			System.out.println("S'il vous plait entrez un nombre :");
			Scanner inputFromConsole = new Scanner(System.in);
			choise= inputFromConsole.nextInt();
			
			if(choise==1)
			{

				System.out.println("Ajouter un payé : ");
				Scanner inputFromConsole1 = new Scanner(System.in);
				String country = inputFromConsole1.next();
				iserviceWorker.AjouterCountry(country);
				iserviceWorker.listCountry();
			}
			else if(choise==2)
			{
				System.out.println("entrez votre code de country : ");
				Scanner inputFromConsole2 = new Scanner(System.in);
				String code = inputFromConsole2.next();
				iserviceWorker.findByCode(code);
				
			}
			else if(choise==3)
			{
				System.out.println("entrez votre code de country : ");
				Scanner inputFromConsole3 = new Scanner(System.in);
				String code = inputFromConsole3.next();
				iserviceWorker.supprimerByCode(code);
				iserviceWorker.listCountry();
			}
			else if(choise==0)
			{

				System.out.print("Merci pour votre utilisation vous étes Sortis de l'application");
				System.exit(0);
			}
			
			
			}}
}
