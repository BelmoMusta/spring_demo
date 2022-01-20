package country.app;

import country.configuration.AppConfiguration;
import country.service.IServiceWorker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class App {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);

		IServiceWorker serviceWorker = applicationContext.getBean(IServiceWorker.class);

		while (true) {
			serviceWorker.showMenu();
			Scanner inputFromConsole = new Scanner(System.in);
			String choix = inputFromConsole.next();
			serviceWorker.dealWithMenuChoice(choix);
		}
	}
	
}
