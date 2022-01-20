package app;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import app.controller.CountryController;
import static app.helper.OPERATION.*;

@SuppressWarnings("all")
public class App {
	public static void main(String[] args) {
		try {
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/beans/database-config.xml");
			CountryController countryController = applicationContext.getBean(CountryController.class);
			System.out.println("-----------------------------------------------------------");
			System.out.println("|   -Pour l'ajout d'un nouveau pays tapper            [1] |");
			System.out.println("|   -Pour lister les informations d'un pays, tapper   [2] |");
			System.out.println("|   -Pour supprimer un pays, tapper                   [3] |");
			System.out.println("|   -Pour modifier des informations d'un pays, tapper [4] |");
			System.out.println("|   -Pour lister tous les pays d'un continent, tapper [5] |");
			System.out.println("|   -Pour sortir de l'application tapper              [0] |");
			System.out.println("-----------------------------------------------------------");
			while (true) {
				
				System.out.print("[0-5] ===> ");

				Scanner inputFromConsole = new Scanner(System.in);
				int inputNumber = inputFromConsole.nextInt();

				switch (inputNumber) {
				case EXIT: {
					countryController.exitApp();
				}
				case ADD_COUNTRY: {
					countryController.addCountry(inputFromConsole);
					break;
				}
				case GET_COUNTRY: {
					countryController.getCountry(inputFromConsole);
					break;
				}
				case DELETE_COUNTRY: {
					countryController.deleteCountry(inputFromConsole);
					break;
				}
				case UPDATE_COUNTRY: {
					countryController.updateCountry(inputFromConsole);
					break;
				}
				case GET_ALL_COUNTRY: {
					countryController.getCountriesByContinent(inputFromConsole);
					break;
				}
				default: {
					System.out.println(
							String.format("Le nombre entrer [%d] ne corresponde pas a une operation , essayer à nouveau . ",
									inputNumber));
				}
				}

			}
			
		} catch (Exception e) {
			System.exit(0);
		} 
	}

}
