package country.app;

import country.service.IServiceWorker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;
import java.sql.SQLException;

@SuppressWarnings("all")
public class App {
	public static void main(String[] args) throws SQLException {
		ApplicationContext applicationContext =
				new ClassPathXmlApplicationContext("beans/*.xml");
		IServiceWorker serviceWorker = applicationContext.getBean(IServiceWorker.class);
		org.h2.tools.Server.createWebServer().start();

		while (true) {
			System.out.println("\n");
			System.out.println("tapez 1 pour l'ajout d'un nouveau pays");
			System.out.println("tapez 0 pour sortir ");
			System.out.print("entrer un nombre:");
			Scanner scanner = new Scanner(System.in);
			int choix = Integer.parseInt(scanner.next());
			switch (choix) {
				case 0: {
					System.exit(0);
				}
				case 1: {
					System.out.print("ajouter le pays sous forme code,name,devise,greetings,continent :");
					Scanner scanner1 = new Scanner(System.in);
					String information = scanner1.next();
					serviceWorker.addCountry(information);
					break;
				}


			}
		}

	}

}
