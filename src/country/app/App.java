package country.app;

import config.Config;
import country.service.IServiceWorker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.Scanner;

@SuppressWarnings("all")
public class App {
	public static void main(String[] args) throws SQLException {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
		IServiceWorker serviceWorker = applicationContext.getBean(IServiceWorker.class);
		org.h2.tools.Server.createWebServer().start();
		while (true) {
			System.out.println("Pour ajouter un pays, appuyez sur 1");
			System.out.println("Pour sélectionner un pays, appuyez sur 2");
			System.out.println("Pour supprimer un pays, appuyez sur 3");
			System.out.println("Pour modifier de pays, appuyez sur 4");
			System.out.println("Pour sélectionner les pays d'un continent, appuyez sur 5");
			System.out.println("Pour sortir, appuyez sur 0");
			Scanner scanner = new Scanner(System.in);
			int intScanner = Integer.parseInt(scanner.next());
			switch (intScanner) {
				case 0: {
					System.exit(0);
				}
				case 1: {
					System.out.print("Saisir le pays à ajouter (code,name,device,greeting,continent) :");
					Scanner input = new Scanner(System.in);
					String information = input.next();
					serviceWorker.addCountry(information);
					break;
				}
				case 2: {
					System.out.print("Saisir le code du pays :");
					Scanner scanner1 = new Scanner(System.in);
					String code = scanner1.next();
					serviceWorker.getCountryInfos(code);
					break;
				}
				case 3: {
					System.out.print("Saisir le code du pays à supprimer:");
					Scanner scanner1 = new Scanner(System.in);
					String code = scanner1.next();
					serviceWorker.deleteCountry(code);
					break;
				}
				case 4: {
					System.out.print("Saisir le code du pays à modifier :");
					Scanner scannerCode = new Scanner(System.in);
					String code = scannerCode.next();
					System.out.print("Saisir les nouvelles informations de pays (code,name,devise,greetings,continent) :");
					Scanner scannerinfo = new Scanner(System.in);
					String info = scannerinfo.next();
					serviceWorker.updateCountry(code,info);
					break;
				}
				default: {
					break;
				}
			}
			
		}
	}
	
}
