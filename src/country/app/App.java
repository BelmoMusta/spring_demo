package country.app;

import country.model.ApplicationConfig;
import country.model.HibernateConfig;
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
AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(HibernateConfig.class);
			IServiceWorker serviceWorker = ctx.getBean(IServiceWorker.class);


			int num=0;
			System.out.println("choisir un nombre pour faire votre manipulation: +");
					
			System.out.println("saisir le numero s'il vous plaiz:");

			Scanner inputFromConsole = new Scanner(System.in);
			num= inputFromConsole.nextInt();

			if(num==1)
			{
				System.out.println("Ajouter un payé: ");
				Scanner input1 = new Scanner(System.in);
				String country = input1.next();
				serviceWorker.AddCountry(country);
				serviceWorker.listCountry();
			}
			else if(num==2)
			{
				System.out.println("entrez votre code de country pour afiicher: ");
				Scanner inputFromConsole2 = new Scanner(System.in);
				String code = inputFromConsole2.next();
				serviceWorker.findByCode(code);
				
			}
			else if(num==3)
			{
				System.out.println("entrez votre code de country pour supprimer : ");
				Scanner inputFromConsole3 = new Scanner(System.in);
				String code = inputFromConsole3.next();
				serviceWorker.deleteByCode(code);
				serviceWorker.listCountry();
			}
			else if(num==4)
			{
				System.out.println("entrez le code de country pour modifier avec nom,devise,greeting  : ");
				Scanner inputFromConsole4 = new Scanner(System.in);
				String code = inputFromConsole4.next();
				Scanner nouvinfo = new Scanner(System.in);
				String nouvvinfo = inputFromConsole4.next();
				serviceWorker.updateByCode(code,nouvvinfo);
				serviceWorker.listCountry();
			}
			
			else if(num==6)
			{

				System.out.print("fin de l'application");
				System.exit(0);
			}


		}
}