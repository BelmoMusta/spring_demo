package country.app;

import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import country.config.HBconfiguration;

@SuppressWarnings("all")
public class App {
	public static void main(String[] args) {

		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
				HBconfiguration.class);
		while (true) {
			System.out.print("Choisir une langue : ");
			Scanner inputFromConsole = new Scanner(System.in);
			String language = inputFromConsole.next();

		}
	}

}
