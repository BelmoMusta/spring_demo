package country.app;
import country.service.IServiceWorker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.sql.SQLException;
import java.util.Scanner;

@SuppressWarnings("all")
public class App {
	public static void main(String[] args) throws SQLException{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans/*.xml");
		IServiceWorker serviceWorker = applicationContext.getBean(IServiceWorker.class);
		org.h2.tools.Server.createWebServer().start();
		while (true) {
			System.out.println("****************************************************************");
			System.out.println("Choisir un nombre:");
			System.out.println("1- Pour ajouter un pays :");
			System.out.println("2- Pour lister ses informations :");
			System.out.println("3- Pour supprimer le pays choisi :");
			System.out.println("4- Pour modifier ses informations :");
			System.out.println("5- Pour lister les pays d'un continent :");
			System.out.println("0- Pour finir :");
			System.out.println("****************************************************************");

			Scanner inputFromConsole1 = new Scanner(System.in);
			int code = Integer.parseInt(inputFromConsole1.next());
			switch (code) {
				case 0: {
					System.exit(0);
				}
				case 1: {
					System.out.print("Entrer le pays à ajouter sous la forme: 'code,nom,devise,greeting,code_de_continent' ");
					Scanner inputFromConsole = new Scanner(System.in);
					String language = inputFromConsole.next();
					serviceWorker.addCountry(language);
					break;
				}
				case 2: {
					System.out.print("Entrer le code du pays à lister: ");
					Scanner inputFromConsole = new Scanner(System.in);
					String language = inputFromConsole.next();
					serviceWorker.getInformations(language);
					break;
				}
				case 3: {
					System.out.print("Entrer le code du pays à supprimer: ");
					Scanner inputFromConsole = new Scanner(System.in);
					String language = inputFromConsole.next();
					serviceWorker.deleteCountry(language);
					break;
				}
				case 4: {
					System.out.print("Entrer le code du pays à MAJ: ");
					Scanner inputFromConsole = new Scanner(System.in);
					String language = inputFromConsole.next();
					System.out.print("Entrer les nouveaux champs sous la forme suivante: 'code,nom,devise,greeting,code_de_continent' ");
					Scanner inputFromConsole2 = new Scanner(System.in);
					String modif = inputFromConsole.next();
					serviceWorker.updateCountry(language,modif);
					break;
				}
				case 5: {
					serviceWorker.getCountries();
					break;
				}
				default:{
					System.out.print("Choisir une langue : ");
					Scanner inputFromConsole = new Scanner(System.in);
					String language = inputFromConsole.next();
					serviceWorker.dealWithCountryByCode(language);
					break;
				}

			}

		}
	}

}
	

