package country.app;

import country.service.IServiceWorker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import configuration.AppConfig;

import java.util.Scanner;

@SuppressWarnings("all")
public class App {
	public static void main(String[] args) {
		
		int chose = 0;
		Scanner inputFromConsole = new Scanner(System.in);
		
		//ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans/*.xml");
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		//applicationContext.scan("country");
		IServiceWorker serviceWorker = applicationContext.getBean(IServiceWorker.class);
		
		//Help user
		System.out.println("Pour Ajouter des informations sur un pays clicker sur 1");
		System.out.println("Pour Voir des informations sur un pays clicker sur 2");
		
		System.out.println("Enter numero :");
		chose = inputFromConsole.nextInt();
		
		switch(chose) {
			case 1 : 
				break;
			case 2 : {
				while (true) {
					System.out.print("Choisir une langue : ");
					String language = inputFromConsole.next();
					serviceWorker.dealWithCountryByCode(language);
				}
				
			}
				
			default :
				break;
		}
	}
	
}
