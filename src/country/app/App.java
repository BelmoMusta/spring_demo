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
            System.out.println("Application Interactive");
            System.out.println("-------------------------------------------------------------");
            System.out.println("Entrez 1 pour ajouter un pays.");
            System.out.println("Entrez 2 pour lister toutes les informations d'un pays.");
            System.out.println("Entrez 3 pour supprimer un pays.");
            System.out.println("Entrez 4 pour modifier les informations d'un pays.");
            System.out.println("Entrez 5 pour lister les pays d'un continent.");
            System.out.println("Entrez 0 pour sortir de l'application.");
            System.out.println("-------------------------------------------------------------");
            System.out.println("\n");
            System.out.println("Veuillez entrer un chiffre s'il vous plaît : ");

            Scanner imputFromConsole = new Scanner(System.in);
            int codesaisi = Integer.parseInt(imputFromConsole.next());

            switch (codesaisi) {

                case 0: {System.exit(0);}

                case 1: {
                    System.out.print("Entrez les informations du pays à ajouter sous le format : 'code, nom, devise, greeting, continent_code'.");
                    System.out.println("\n");
                    Scanner inputFromConsole = new Scanner(System.in);
                    String pays = inputFromConsole.next();
                    serviceWorker.addCountry(pays);
                    break;
                }
                case 2: {

                    System.out.println("Entrez le code du pays dont vous voulez lister les informations : ");
                    //System.out.println("\n");
                    Scanner inputFromConsole = new Scanner(System.in);
                    String pays = inputFromConsole.next();
                    serviceWorker.getCountryInfos(pays);
                    break;
                }
                case 3: {
                    System.out.println("Entrez le code du pays à supprimer : ");
                    //System.out.println("\n");
                    Scanner inputFromConsole = new Scanner(System.in);
                    String pays = inputFromConsole.next();
                    serviceWorker.deleteCountry(pays);
                    break;
                }
                case 4:{
                    System.out.println("Entrez le code du pays à modifier : ");
                    //System.out.println("\n");
                    Scanner inputFromConsole = new Scanner(System.in);
                    String pays = inputFromConsole.next();
                    System.out.println("Entrez les nouvelles valeurs des champs du pays sous la forme: 'code, name, devise, greetings, continent_code'");
                    Scanner inputFromConsole_update = new Scanner(System.in);
                    String update = inputFromConsole_update.next();
                    serviceWorker.updateCountry(pays,update);
                    break;
                }
                case 5:{
                    System.out.println("Entrez le code du continent dont vous voulez afficher les pays : ");
                    Scanner inputFromConsole =new Scanner(System.in);
                    String pays = inputFromConsole.next();
                    serviceWorker.getCountries(pays);
                    break;
                }
                default:{
                    System.out.print("Choisir une langue : ");
                    Scanner inputFromConsole = new Scanner(System.in);
                    String pays = inputFromConsole.next();
                    serviceWorker.dealWithCountryByCode(pays);
                    break;
                }
            }




			
		}
	}
	
}
