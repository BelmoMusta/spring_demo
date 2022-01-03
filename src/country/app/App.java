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
		
		int chose = 0;
		String information;
		Scanner inputFromConsole = new Scanner(System.in);
		
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		IServiceWorker serviceWorker = applicationContext.getBean(IServiceWorker.class);
		
		//Help user
		System.out.println("Pour Ajouter des informations sur un pays clicker sur 1");
		System.out.println("Pour Voir des informations sur un pays clicker sur 2");
		
		System.out.println("Enter numero :");
		chose = inputFromConsole.nextInt();
		
		switch(chose) {
			case 1 : 
				System.out.print("Entrer les informations sous forme : code,name,devise,greetings");
				String informations = inputFromConsole.next();
				serviceWorker.addCountry(informations);
				break;
			case 2 : {
					System.out.print("Choisir une langue : ");
					String language = inputFromConsole.next();
					serviceWorker.dealWithCountryByCode(language);
			}
			default :
				break;
		}
	}
	
}
