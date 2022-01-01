package country;

import java.util.Scanner;
// I changed this for pull request purpose

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SuppressWarnings("all")
public class App {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"/beans/*.xml",
				"beans/france.xml",
				"beans/england.xml",
				"beans/spain.xml",
				"beans/germany.xml");
		while (true) {
			System.out.print("Choisir une langue : ");
			Scanner inputFromConsole = new Scanner(System.in);
			String language = inputFromConsole.next();
			
			String beanId;
			if ("fr".equals(language)) {
				beanId = "frenchWelcomeService";
			} else if ("en".equals(language)) {
				beanId = "englishWelcomeService";
			} else if ("es".equals(language)) {
				beanId = "spainWelcomeService";
			} else if ("de".equals(language)) {
				beanId = "germanWelcomeService";
			} else {
				break; // exit the program
			}
			
			CountryService country = applicationContext.getBean(beanId, CountryService.class);
			String welcome = country.welcome();
			System.out.println(welcome);
			
		}
		// I changed this for pull request purpose
	}
}
