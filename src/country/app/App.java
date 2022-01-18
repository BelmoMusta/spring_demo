package country.app;

import country.service.IServiceWorker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

@SuppressWarnings("all")
public class App {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans/*.xml");
		IServiceWorker serviceWorker = applicationContext.getBean(IServiceWorker.class);
		for (int i = 0; i < 100; i++)
			System.out.print("*");

		System.out.println("\nDevoir TP N°2\t\t\t\t\t\t\t\t\t\tHAFID Mouaad");
		for (int i = 0; i < 100; i++)
			System.out.print("*");
		System.out.println("\nBIENVENUE !!\n");
		while (true) {
			System.out.println("\nMENU:");
			System.out.println("\n\t1- Ajouter un nouveau pays.");
			System.out.println("\t2- Lister les informations d'un pays.");
			System.out.println("\t3- Supprimer un pays.");
			System.out.println("\t4- Modifier les informations d'un pays.");
			System.out.println("\t5- Lister les pays d'un continent.");
			System.out.println("\t0- Sortir de l'application.");
			Scanner inputFromConsole = new Scanner(System.in);
			System.out.print("\nChoisissez une option: ");
			String option = inputFromConsole.next();
			switch (option) {
				case "1":
					for (int i = 0; i < 50; i++)
						System.out.print("/\\");
					System.out.println("\nAjout d'un nouveau pays:");
					System.out.print(
							"\nEntrer les informations requis sous le format suivant (code,nom,devise,greeting,code continent): ");
					String countryToAdd = inputFromConsole.next();
					serviceWorker.addCountry(countryToAdd);
					for (int i = 0; i < 50; i++)
						System.out.print("\\/");
					break;
				case "2":
					for (int i = 0; i < 50; i++)
						System.out.print("/\\");
					System.out.println("\nInformations d'un pays:");
					System.out.println("\tAspect Fonctionnel 2 (WIP)");
					for (int i = 0; i < 50; i++)
						System.out.print("\\/");
					break;
				case "3":
					for (int i = 0; i < 50; i++)
						System.out.print("/\\");
					System.out.println("\nSuppression d'un pays:");
					System.out.println("\tAspect Fonctionnel 3 (WIP)");
					for (int i = 0; i < 50; i++)
						System.out.print("\\/");
					break;
				case "4":
					for (int i = 0; i < 50; i++)
						System.out.print("/\\");
					System.out.println("\nModification d'un pays:");
					System.out.println("\tAspect Fonctionnel 4 (WIP)");
					for (int i = 0; i < 50; i++)
						System.out.print("\\/");
					break;
				case "5":
					for (int i = 0; i < 50; i++)
						System.out.print("/\\");
					System.out.println("\nPays d'un continent:");
					System.out.println("\tAspect Fonctionnel 5 (WIP)");
					for (int i = 0; i < 50; i++)
						System.out.print("\\/");
					break;
				case "0":
					for (int i = 0; i < 50; i++)
						System.out.print("/\\");
					System.out.println("\nSortie de l'application...");
					System.exit(0);
					break;
				default:
					break;
			}
		}
	}

}
