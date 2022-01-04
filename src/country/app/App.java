package country.app;

import country.controller.CountryController;
import country.helper.OPERATION;
import country.model.Country;
import country.service.IServiceWorker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

@SuppressWarnings("all")
@ComponentScan(value = {"country","continent"})
public class App {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/beans/database-config.xml");
		IServiceWorker serviceWorker = applicationContext.getBean(IServiceWorker.class);
		CountryController countryController = applicationContext.getBean(CountryController.class);
		while (true) {
			System.out.println("-----------------------------------------------------------");
			System.out.println("|   -Pour l'ajout d'un nouveau pays tapper            [1] |");
			System.out.println("|   -Pour lister les informations d'un pays, tapper   [2] |");
			System.out.println("|   -Pour supprimer un pays, tapper                   [3] |");
			System.out.println("|   -Pour modifier des informations d'un pays, tapper [4] |");
			System.out.println("|   -Pour lister tous les pays d'un continent, tapper [5] |");
			System.out.println("|   -Pour sortir de l'application tapper              [0] |");
			System.out.println("-----------------------------------------------------------");
			System.out.print("=====>");

			Scanner inputFromConsole = new Scanner(System.in);
			int inputNumber = inputFromConsole.nextInt();

			switch (inputNumber) {
			case OPERATION.EXIT: {
				countryController.exitApp();
			}
			case OPERATION.ADD_COUNTRY: {
				countryController.addCountry(inputFromConsole);
				break;
			}
			case OPERATION.GET_COUNTRY: {
				countryController.getCountry(inputFromConsole);
				break;
			}
			case OPERATION.DELETE_COUNTRY: {
				countryController.deleteCountry(inputFromConsole);
				break;
			}
			case OPERATION.UPDATE_COUNTRY: {
				countryController.updateCountry(inputFromConsole);
				break;
			}
			case OPERATION.GET_ALL_COUNTRY: {
				// cette partie vas etre realiser dans la version Hibernate ...
			}
			default: {
				System.out.println(
						String.format("Le nombre entrer [%d] ne corresponde pas a une operation , essayer à nouveau . ",
								inputNumber));
			}
			}

		}
	}

}
