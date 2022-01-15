package country.app;

import country.service.IServiceWorker;

import org.h2.tools.Server;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.util.Scanner;

@SuppressWarnings("all")
public class App {
	public static void main(String[] args) throws SQLException {
		ApplicationContext applicationContext =
				new ClassPathXmlApplicationContext("beans/*.xml");
//				new AnnotationConfigApplicationContext("country");				
		IServiceWorker serviceWorker = applicationContext.getBean(IServiceWorker.class);
		Server.createWebServer().start();
		while(true) {
			System.out.println(
				"# Pour ajouter un nouveau pays, tapper 1 :\n"
				+ "# Pour lister les informations d'un pays, tapper 2 :\n"
				+ "# Pour supprimer un pays, tapper 3 :\n"
				+ "# Pour modifier des infos d'un pays, tapper 4 :\n"
				+ "# Pour lister tous les pays d'un continent, tapper 5 :\n"
				+ "# Pour sortir de l'application tapper 0 :");
			Scanner It = new Scanner(System.in);
			switch(It.nextInt()) {
				case 1:
					System.out.println("saisi informations de pays, sous forme \"name,code,currency,greetings,code-continent\" :");
					Scanner Ss = new Scanner(System.in);
					String infos = Ss.next();
					serviceWorker.dealWithSaveCountry(infos);
					break;
				case 2:				
					System.out.print("Choisir une langue : ");
					Scanner inputFromConsole = new Scanner(System.in);
					String language = inputFromConsole.next();
					serviceWorker.dealWithCountryByCode(language);
					break;
				case 3:
					System.out.println("choisir une langue : ");
					Scanner inputOfDelete = new Scanner(System.in);
					String lang = inputOfDelete.next();
					serviceWorker.dealWithDeleteCountry(lang);
					break;
				case 4:
					System.out.println("choisir une langue : ");
					Scanner codeOfCountry = new Scanner(System.in);
					String code = codeOfCountry.next();
					System.out.println("saisi informations de pays, sous forme \"name,code,currency,greetings,code-continent\" :");
					Scanner sNewInfos = new Scanner(System.in);
					String newInfos = sNewInfos.next();
					serviceWorker.dealWithUpdateCountry(newInfos, code);
					break;
				case 5:
					System.out.println("choisir un continent :");
					Scanner sContinent = new Scanner(System.in);
					String codeContinent = sContinent.next();
					serviceWorker.dealWithContinents(codeContinent);
					break;
				case 0:
					System.out.println("exit de programme");
					System.exit(0);
					
					break;
				default:
					System.out.println("veuillez entrer un numéro s'il vous plaît! :");
			}
		}
						
	}
	
}
