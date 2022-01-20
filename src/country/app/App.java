package country.app;

import country.model.Country;
import country.service.IServiceWorker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import Configuration.Config;

import java.util.Scanner;

@SuppressWarnings("all")
public class App {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
		IServiceWorker sw = applicationContext.getBean(IServiceWorker.class);
		int choix=0;
		while (true) {
			System.out.println("\n1- Pour l'ajout d'un nouveau pays tapper 1,\n" + 
					"2- Pour lister les informations d'un pays, tapper 2,\n" + 
					"3- Pour supprimer un pays, tapper 3,\n" + 
					"4- Pour modifier des informations d'un pays, tapper 4, \n" + 
					"5- Pour lister tous les pays d'un continent, tapper 5, \n" + 
					"6- Pour sortir de l'application tapper 0;");
			
			Scanner input = new Scanner(System.in);
			choix= input.nextInt();
			if(choix==0)
			{
				System.out.println("Application exit");
				System.exit(0);
			}
			else if(choix==1)
			{
				System.out.println("Suivez le schema suivant (FR,france,EURO,Bonjour!) pour ajouter un paye ");
				Scanner input1 = new Scanner(System.in);
				String paye = input1.next();
				sw.addCountry(paye);	
				sw.displayCountry();
			}
			else if(choix==2)
			{
				System.out.println("entrez le code du pays à afficher : ");
				Scanner input1 = new Scanner(System.in);
				String codeContry = input1.next();
				sw.displayByCode(codeContry);

			}
			else if (choix==3) {
				System.out.println("entrez le code du pays à Supprimmer : ");
				Scanner input1 = new Scanner(System.in);
				String codeContry = input1.next();
				sw.deletByCode(codeContry);
				sw.displayCountry();
				
			}
			
		}
	}
	
}
