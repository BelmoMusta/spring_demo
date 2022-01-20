package country.app;

import country.model.Country;
import country.service.IServiceWorker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.util.Scanner;

@SuppressWarnings("all")
public class App {
	public static void main(String[] args) throws SQLException {

		ApplicationContext applicationContext =
				new AnnotationConfigApplicationContext("country");
		IServiceWorker serviceWorker = applicationContext.getBean(IServiceWorker.class);
		Scanner inputFromConsole = new Scanner(System.in);
		org.h2.tools.Server.createWebServer().start();
		while (true) {
			
			/*
			 * 
			 * 1- Pour l'ajout d'un nouveau pays tapper 1, L'utilisateur sera amené à saisir les information du pays une par une
    Bonus : si vous le souhaitez, vous pouvez faire l'ajout par une seule ligne avec un séparateur, exemple : FR,france,EURO,Bonjour!
2- Pour lister les informations d'un pays, tapper 2, (le code pays sera saisi par l'utilisateur).
3- Pour supprimer un pays, tapper 3, (le code pays sera saisi par l'utilisateur).
4- Pour modifier des informations d'un pays, tapper 4, (le code pays sera saisi par l'utilisateur).
5- Pour lister tous les pays d'un continent, tapper 5, (le code du continent sera saisi par l'utilisateur).
6- Pour sortir de l'application tapper 0;
			 * 
			 */
			System.out.println("configuration hibernate ");
		    String query = inputFromConsole.next();

	}

}
}
