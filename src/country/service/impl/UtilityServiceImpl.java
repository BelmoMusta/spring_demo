package country.service.impl;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import country.dao.ContinentDAO;
import country.model.Continent;
import country.model.Country;
import country.service.IUtilityService;

@SuppressWarnings("all")
@Service
public class UtilityServiceImpl implements IUtilityService {
    @Autowired
    private ContinentDAO continentDAO;

    @Override
    public Country updateSubMenu(Country country) {
        while (true) {
            System.out
                    .println("Etat actuel du pays en cours de modification:\n\tCode: " + country.getCode()
                            + "\n\tNom: "
                            + country.getName()
                            + "\n\tDevise: " + country.getDevise() + "\n\tGreeting: " + country.getGreetings()
                            + "\n\tContinent : "
                            + country.getContinent().getName());
            System.out.println("\nChoisir le champ à modifier:");
            System.out.println("\t1- Code");
            System.out.println("\t2- Nom");
            System.out.println("\t3- Devise");
            System.out.println("\t4- Greeting");
            System.out.println("\t5- Continent");
            System.out.println("\n\tQ- Annuler\tC- Confirmer les modifications.");
            System.out.print("Choisir un champ: ");
            Scanner inputFromConsole = new Scanner(System.in);
            String option = inputFromConsole.next();

            switch (option) {
                case "1":
                    System.out.print("Code : ");
                    String code = inputFromConsole.next();
                    country.setCode(code);
                    break;
                case "2":
                    System.out.print("Nom : ");
                    String nom = inputFromConsole.next();
                    country.setName(nom);
                    break;
                case "3":
                    System.out.print("Devise : ");
                    String devise = inputFromConsole.next();
                    country.setDevise(devise);
                    break;
                case "4":
                    System.out.print("Greeting : ");
                    String greetings = inputFromConsole.next();
                    country.setGreetings(greetings);
                    break;
                case "5":
                    System.out.print("Code continent (af,eu,na,sa,as,au,an): ");
                    String codeContinent = inputFromConsole.next();
                    Continent continent = continentDAO.getContinentByCode(codeContinent);
                    if (continent != null)
                        country.setContinent(continent);
                    else
                        System.out.println("Continent introuvable");
                    break;
                case "q":
                    return null;

                case "Q":
                    return null;
                case "C":
                    return country;
                case "c":
                    return country;
                default:
                    break;
            }

        }
    }
}
