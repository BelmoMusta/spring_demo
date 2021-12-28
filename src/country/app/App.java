package country.app;

import country.service.IServiceWorker;

import org.h2.tools.Server;
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
		
		System.out.println(
				"# Pour ajouter un nouveau pays, tapper 1 :\n"
				+ "# Pour lister les informations d'un pays, tapper 2 :\n"
				+ "# Pour supprimer un pays, tapper 3 :\n"
				+ "# Pour modifier des infos d'un pays, tapper 4 :\n"
				+ "# Pour lister tous les pays d'un continent, tapper 5 :\n"
				+ "# Pour sortir de l'application tapper 0 :");
		Scanner It = new Scanner(System.in);
		Server.createWebServer().start();
		if(It.nextInt() == 1) {
			while (true) {
				System.out.println("saisi informations de pays, sous forme \"name,code,currency,greetings\" :");
				Scanner Ss = new Scanner(System.in);
				String infos = Ss.next();
				serviceWorker.dealWithSaveCountry(infos);
			}
		}else if (It.nextInt() == 2) {
			while (true) {
				System.out.print("Choisir une langue : ");
				Scanner inputFromConsole = new Scanner(System.in);
				String language = inputFromConsole.next();
				serviceWorker.dealWithCountryByCode(language);
				
			}
		}else if(It.nextInt() == 0) {
			System.exit(0);
		}
	}
	
}
