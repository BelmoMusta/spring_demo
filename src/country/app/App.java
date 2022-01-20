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

            System.out.println("Veuillez entrer un chiffre s'il vous plaît");
            System.out.println("Entrez 1 pour ajouer un pays");

            Scanner imputFromConsole = new Scanner((System.in));
            int codesaisi = Integer.parseInt(imputFromConsole.next());

            switch (codesaisi) {
                case 1: {
                    System.out.print("Entrez le pays à ajouter sous le format : code,nom,devise,greeting,continent code");
                    Scanner inputFromConsole = new Scanner(System.in);
                    String pays = inputFromConsole.next();
                    serviceWorker.addCountry(pays);
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
