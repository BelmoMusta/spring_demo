package country.app;

import country.service.IServiceWorker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.util.Scanner;

@SuppressWarnings("all")
public class App {
	public static void main(String[] args) {
		ApplicationContext applicationContext =
				new ClassPathXmlApplicationContext("beans/*.xml");
		IServiceWorker serviceWorker = applicationContext.getBean(IServiceWorker.class);
		while (true) {
			System.out.print("Tapper le numéro de la commande : ");
			Scanner inputFromConsoleNumber = new Scanner(System.in);
			String n = inputFromConsoleNumber.next();
			if("1".equals(n)) {
				System.out.print("Pour ajouter un pays saisir les informations du pays sous format (nom,code,devise,greeting) : ");
				Scanner inputFromConsole = new Scanner(System.in);
				String info = inputFromConsole.next();
				String[] str = info.split(",");
				serviceWorker.dealWithAddCountry(str[0], str[1], str[2], str[3]);
			}
			if("2".equals(n)) {
				System.out.print("Saisir code pays à afficher : ");
				Scanner inputFromConsole = new Scanner(System.in);
				String code = inputFromConsole.next();
				serviceWorker.dealWithCountryByCode(code);
			}
			else if("3".equals(n)) {
				System.out.print("Saisir code pays à supprimer : ");
				Scanner inputFromConsole = new Scanner(System.in);
				String code = inputFromConsole.next();
				serviceWorker.dealWithDeleteCountry(code);
			}
			else if("4".equals(n)) {
				System.out.print("Saisir code pays à modifier : ");
				Scanner inputFromConsole = new Scanner(System.in);
				String code = inputFromConsole.next();
				
				System.out.print("saisir les nouvelles informations du pays sous format (nom,devise,greeting) : ");
				Scanner inputFromConsolem = new Scanner(System.in);
				String info = inputFromConsolem.next();
				String[] str = info.split(",");
				
				serviceWorker.dealWithUpdateCountry(code, str[0], str[1], str[2]);
			}
			else if("5".equals(n)) {
				System.out.print("Pour afficher tous les pays d'un continent, saisir code continent: ");
				Scanner inputFromConsole = new Scanner(System.in);
				String code = inputFromConsole.next();
				
				serviceWorker.dealWithListAllCountries(code);
			}
			else if("6".equals(n)) {
				//System.out.print("Pour afficher tous les pays d'un continent, saisir code continent: ");
				//Scanner inputFromConsole = new Scanner(System.in);
				//String code = inputFromConsole.next();
				try{serviceWorker.dealWithListAll();
				} catch (SQLException e) {
		            e.printStackTrace();
		        }       

			}
			else if("0".equals(n)) {
				System.exit(0);	
			}
			
		}
	}
	
}
