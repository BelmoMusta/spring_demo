package country.app;

import country.model.Country;
import country.service.IServiceWorker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.Scanner;

@SuppressWarnings("all")
@EnableTransactionManagement
public class App {
	public static void main(String[] args) {
		ApplicationContext applicationContext =
				new ClassPathXmlApplicationContext("beans/*.xml");
		IServiceWorker serviceWorker = applicationContext.getBean(IServiceWorker.class);
		while (true) {
			System.out.println("\n");
			System.out.println("-----------------------------------------------------------------------------------------------------------");
			System.out.println("\n1-pour l'ajout d'un nouveau pays");
			System.out.println("2- Pour lister les informations d'un pays");
			System.out.println("3- Pour supprimer un pays");
			System.out.println("4- Pour modifier des informations d'un pays");
			System.out.println("5- Pour lister tous les pays d'un continent");
			System.out.println("0- pour sortir de l'application\n");
			System.out.print("entrer un nombre de 0 à 5:");
			Scanner inputFromConsole = new Scanner(System.in);
			String code = inputFromConsole.next();

			switch (code){
				case "0":

					break;
				case "1":
					//ajout d'un nouveau pays
					System.out.print("Entrer le pays à ajouter sous le format suivant: ");
					String country = inputFromConsole.next();
					serviceWorker.addCountry(country);
					break;
				case "2":
					serviceWorker.getAllCountries();
					break;
				case "3":
					break;
				case "4":
					break;
				case "5":
					break;

			}


		}
	}
	
}
