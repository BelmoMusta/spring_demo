package country.app;

import country.helper.OPERATION;
import country.model.Country;
import country.service.IServiceWorker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

@SuppressWarnings("all")
public class App {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans/*.xml");
		IServiceWorker serviceWorker = applicationContext.getBean(IServiceWorker.class);
		
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
						System.out.println("\n\n");
						System.out.println("=============");
						System.out.println("Bay Bay......");
						System.out.println("=============");
						System.exit(0);
					}
					case OPERATION.ADD_COUNTRY: {
						System.out.println("Ajouter un nouveau pays (code,nom,devise,salutation): ");
						while (true) {
							if(inputFromConsole.hasNext()) {							
								String pays = inputFromConsole.next();
								String[] _pays = pays.split(",");
								if(_pays.length!=4) {
									System.out.print("!! le format tappait est inccorect , essayer à nouveau : ");
									continue;
								}else {
									Country country = serviceWorker.getCountry(_pays[0]);
									if(country==null) {
										country = new Country();
										country.setCode(_pays[0]);
										country.setName(_pays[1]);
										country.setDevise(_pays[2]);
										country.setGreetings(_pays[3]);
										
										serviceWorker.addCountry(country);
										break;
									}else {
										System.out.println("Ce pays et deja existe....");
										break;
									}
								}
								
							}
							break;
						}						
						break;
					}
					case OPERATION.GET_COUNTRY: {						
						System.out.print("Tappez le code pays que vous voulez lister ses informations: ");
						while (true) {
							if(inputFromConsole.hasNext()) {							
								String code = inputFromConsole.next();
								Country country = serviceWorker.getCountry(code);
								if(country==null) {
									System.out.print("Le pays n'existe pas , tappez un autre code correct : ");
								}else {
									System.out.println(country.toString());
									break;
								}
								
							}
						}						
						break;
					}
					case OPERATION.DELETE_COUNTRY: {
						System.out.print("Tappez le code pays que vous voulez supprimer : ");
						while (true) {
							if(inputFromConsole.hasNext()) {							
								String code = inputFromConsole.next();
								Country country = serviceWorker.getCountry(code);
								if(country==null) {
									System.out.print("Le pays n'existe pas , tappez un autre code correct : ");
								}else {
									serviceWorker.deleteCountry(code);
									System.out.println("Le pays est bien supprimer ");
									break;
								}
								
							}
						}						
						break;
					}
					case OPERATION.UPDATE_COUNTRY: {
						System.out.print("Entrer le code de pays que vous souhaitez modifier : ");
						while (true) {
							if(inputFromConsole.hasNext()) {
								
								String code = inputFromConsole.next();
								Country country = serviceWorker.getCountry(code);
								if(country==null) {
									System.out.print("Le pays n'existe pas , tappez un autre code correct :");
									continue;
								}else {
									System.out.print(String.format("Tappez les nouveaux informations de pays [%s] : ", country.getName()) );
									while(inputFromConsole.hasNext()) {
										String pays = inputFromConsole.next();
										String[] _pays = pays.split(",");
										if(_pays.length!=4) {
											System.out.print("!! le format tappait est inccorect , essayer à nouveau : ");
										}else {
											country = new Country();
											country.setCode(_pays[0]);
											country.setName(_pays[1]);
											country.setDevise(_pays[2]);
											country.setGreetings(_pays[3]);
											
											serviceWorker.updateCountry(country, code);
											break;
										}
									}
									break;
									
								}
								
								
							}
							break;
						}						
						break;
					}
					case OPERATION.GET_ALL_COUNTRY: {
						//cette partie vas etre realiser dans la version Hibernate ...
					}
					default:
						{
							System.out.println(String.format("Le nombre entrer [%d] ne corresponde pas a une operation , essayer à nouveau . ", inputNumber));
						}
			}

		}
	}

}
