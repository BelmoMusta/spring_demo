package country.app;

import country.configurationHibernate.AppConfig;
import country.configurationHibernate.*;

import country.model.Country;

import country.service.IServiceWorker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

@SuppressWarnings("all")
public class App {
		public static void main(String[] args) {
			AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConfigurationHibernate.class);
			IServiceWorker serviceWorker = applicationContext.getBean(IServiceWorker.class);
			
			
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
				serviceWorker.AjouterCountry(country);
				serviceWorker.listCountry();
			}
			else if(choise==2)
			{
				System.out.println("entrez votre code de country : ");
				Scanner inputFromConsole2 = new Scanner(System.in);
				String code = inputFromConsole2.next();
				serviceWorker.findByCode(code);
				
			}
			else if(choise==0)
			{

				System.out.print("Vous étes sortis de l'application");
				System.exit(0);
			}
			
			
			}}
}

