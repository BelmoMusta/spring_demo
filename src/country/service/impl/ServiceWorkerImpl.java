package country.service.impl;

import country.dao.CountryDAO;
import country.model.Country;
import country.service.ICountryService;
import country.service.IServiceWorker;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServiceWorkerImpl implements IServiceWorker {
	@Autowired
	private CountryDAO countryDAO;
	@Autowired
	private ApplicationContext applicationContext;
	
	@Override
	public void dealWithCountryByCode(String language) {
		Country pays = countryDAO.getByCode(language);
		// car c'est prototype
		//ICountryService countryService = applicationContext.getBean(pays.getName(), ICountryService.class);
		
		System.out.println("Code Pays : " + pays.getCode());
		System.out.println("Nom du Pays : " + pays.getName());
		System.out.println("Devise : " + pays.getDevise());
		System.out.println("Greetings : " + pays.getGreetings());

	}

	@Override
    @Transactional
	public void dealWithAddCountry(String nom, String code, String devise, String greeting) {
		Country pays = countryDAO.addCountry(nom, code, devise, greeting);
		//System.out.println("Pays "+pays.getCode()+" ajoutée!");
	}

	@Override
	public void dealWithDeleteCountry(String code) {
		countryDAO.deleteCountry(code);
		//System.out.println("Pays supprimé!");
	}

	@Override
	public void dealWithUpdateCountry(String code, String nom, String devise, String greeting) {
		countryDAO.updateCountry(code, nom, devise, greeting);
		//System.out.println("Pays modifie!");
	}

	@Override
	public void dealWithListAllCountries(String codeContinent) {
		List<Country> countries = countryDAO.listAllCountries(codeContinent);
		for(int i=0; i<countries.size(); i++) {
			System.out.println("Code Pays : " + countries.get(0).getCode()+"Nom du Pays : " + countries.get(0).getName()
			+"Devise : " + countries.get(0).getDevise()+"Greetings : " + countries.get(0).getGreetings());
		}
	}

	@Override
	public void dealWithListAll() throws SQLException  {
		List<Country> countries;
		
        try {
        	countries = countryDAO.listAll();
            for (Country c : countries) {
        		System.out.println("Code Pays : " + c.getCode()+" Nom du Pays : " + c.getName()
        			+" Devise : " + c.getDevise()+" Greetings : " + c.getGreetings());
            }
        }finally {
        }
	}
}
