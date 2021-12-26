package country.app;

import country.service.IServiceWorker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.util.Scanner;

@SuppressWarnings("all")
public class App {
	public static void main(String[] args) throws SQLException {
		ApplicationContext applicationContext =
				new ClassPathXmlApplicationContext("beans/*.xml");
		IServiceWorker serviceWorker = applicationContext.getBean(IServiceWorker.class);
		org.h2.tools.Server.createWebServer().start();
		System.out.println(App.class.getClassLoader().getResource("logging.properties"));
		boolean isRunning = true;
		while (isRunning) {
			System.out.println("*********************************************");
			System.out.println("*** Pour ajouter un nouveau pays tapper 1 ***");
			System.out.println("*** Pour lister les informations d'un pays tapper 2 ***");
			System.out.println("*** Pour sortir de l'application tapper 0 ***");
			Scanner inputFromConsole = new Scanner(System.in);
			String numberEntred = inputFromConsole.next();
			switch (numberEntred) {
			case "1":
				System.out.println("** Veillez saisir les information du pays Ex : FR,france,EURO,Bonjour **");
				String countryInfos = inputFromConsole.next();
				serviceWorker.addCountry(countryInfos);
				break;
				
			case "2":
				System.out.println("** Veillez saisir le code du pays **");
				String countryCode = inputFromConsole.next();
				serviceWorker.dealWithCountryByCode(countryCode);
				break;
				
			case "0":
				isRunning = false;
				break;
			default:
				break;
			}
			
		}
	}
	
}
