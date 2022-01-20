package country.service.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import country.dao.CountryDAO;
import country.model.Country;
import country.service.IServiceWorker;

@Service
public class ServiceWorkerImpl implements IServiceWorker {

	

	@Autowired
	private CountryDAO countryDAO;

	@Override
	public void listCountry()
	{
		countryDAO.listCountry();	
		for (Iterator iterator =countryDAO.listCountry().iterator(); 
				iterator.hasNext();){
				Country country = (Country) iterator.next();
				System.out.print(" Nom: " + country.getName());
				System.out.print(" ,Devise: " + country.getDevise());
				System.out.print(" ,Greetings: " + country.getGreetings());
				System.out.println(",Code: " + country.getCode());
		}
		
	}

	
	@Override
	public void AjouterCountry(String country) {
		String[] list=country.split(",");
		Country countrry=new Country();
		countrry.setCode(list[0]);
		countrry.setName(list[1]);
		countrry.setDevise(list[2]);
		countrry.setGreetings(list[3]);

		countryDAO.enregistrerCountry(countrry);
		

	}


	@Override
	public Country findByCode(String code) {
			return countryDAO.findByCode(code);
		
		
	}
	@Override
	public void supprimerByCode(String code)
	{
		countryDAO.supprimerByCode(code);
	}
	@Override
	public void modificationByCode(String code, String country) {
		String[] list=country.split(",");
		Country countrry=new Country();
		countrry.setName(list[0]);
		countrry.setDevise(list[1]);
		countrry.setGreetings(list[2]);

		countryDAO.modificationByCode(code,countrry);
	
	}


	@Override
	public List<Country> findCountryByContinent(String codecontin) {
		return countryDAO.findCountryByContinent(codecontin);
		
	}

}
