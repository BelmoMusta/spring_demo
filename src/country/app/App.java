package country.app;

import country.config.Config;
import country.service.IGlobalService;
import country.service.IServiceDetail;

import org.h2.tools.Server;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.util.Scanner;

@SuppressWarnings("all")
public class App {
	public static void main(String[] args) throws SQLException {
		ApplicationContext applicationContext =				
				new AnnotationConfigApplicationContext(Config.class);
		IGlobalService serviceGlobal = applicationContext.getBean(IGlobalService.class);
		Server.createWebServer().start();
		while(true) {
			System.out.println(
				"-if you want to add a new country, click 1 :\n"
				+ "-- to see information of a country , click 2 than the code of country :\n"
				+ "--- to delete a country, click 3 than the code of country :\n"
				+ "---- to upfate a country , click 4 than new country with same code :\n"
				+ "----- to see country of a countiener, click 5 :\n"
				+ "------to exit click 0 :");
			Scanner scan = new Scanner(System.in);
			String inputIndex=scan.nextLine();
			serviceGlobal.traitement(inputIndex);
		}
						
	}
	
}
