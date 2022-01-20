package country.app;
import country.Configuration.ApplicationConfig;
import country.Configuration.HibernateConfig;
import country.model.Country;
import country.service.IServiceWorker;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.*;

@SuppressWarnings("all")
public class App {
		public static void main(String[] args) {
            AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(HibernateConfig.class);
			IServiceWorker sw = ctx.getBean(IServiceWorker.class);

        
			int num=0;
			System.out.println("****choose a number: 1 add country, 2 display country , 3 delete country , 4 modify, 0 end ****");
			System.out.println("-----inter the number:----");
			Scanner inputFromConsole = new Scanner(System.in);
		    num= inputFromConsole.nextInt();
		    
      switch(num) {
		
      case 1 :
				System.out.println("****Add a country respecting the following form : code,name,currency,greetings:****");
				Scanner input1 = new Scanner(System.in);
				String country = input1.next();
				sw.AddCountry(country);
				sw.listCountry();
			break;
	
      case 2 :
			
				System.out.println("***inter the country's code: *****");
				Scanner input2 = new Scanner(System.in);
				String code = input2.next();
				sw.findByCode(code);
				
			break;
      
      case 3 :
			
				System.out.println("*****delete country by code: *****");
				Scanner input3 = new Scanner(System.in);
				String code1 = input3.next();
				sw.deleteByCode(code1);
				sw.listCountry();
			break;
      
      case 4 :
			
				System.out.println("*****intrer the code to modify the country  : *****");
				Scanner input4 = new Scanner(System.in);
				String code2 = input4.next();
				System.out.println("----respect the format : name,currency,greeting  : ----");
				Scanner ninfo = new Scanner(System.in);
				String ninfo2 = input4.next();
				sw.updateByCode(code2,ninfo2);
				sw.listCountry();
			break;
			
			
     /* case 5: 
			System.out.print("Entrer le code du continent pour afficher ses pays: ");
			Scanner input5 = new Scanner(System.in);
			String language = input5.next();
			sw.getCountriesByContinent(language);
			break;
			*/
      case 0 :

				System.out.print("*****end of the application*****");
				System.exit(0);
		    break;
      
	  default : 
		  System.out.println("----their is no operation for this number-----");
		}
}}