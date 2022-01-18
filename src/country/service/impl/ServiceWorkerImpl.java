package country.service.impl;

import country.dao.CountryDAO;
import country.model.Country;
<<<<<<< HEAD
import country.service.ICountryService;
import country.service.IServiceWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
=======

import country.service.IServiceWorker;
import org.springframework.beans.factory.annotation.Autowired;

>>>>>>> origin/aspect-fonctionnel-01
import org.springframework.stereotype.Service;

@Service
public class ServiceWorkerImpl implements IServiceWorker {
<<<<<<< HEAD
	@Autowired
	private CountryDAO countryDAO;
	@Autowired
	private ApplicationContext applicationContext;
	
	@Override
	public void dealWithCountryByCode(String language) {
		Country pays = countryDAO.getByCode(language);
		// car c'est prototype
		ICountryService countryService = applicationContext.getBean(ICountryService.class, pays);
		
		System.out.println("WELCOME : " + countryService.welcome());
		System.out.println("Devise is :" + countryService.devise());
=======
	
	@Autowired
	private CountryDAO countryDAO;

	@Override
	public void listCountry()
	{
		countryDAO.listCountry();	
		
	}

	
	@Override
	public void AjouterCountry(String country) {
		String[] list=country.split(",");
		Country countrry=new Country();
		countrry.setCode(list[0]);
		countrry.setDevise(list[2]);
		countrry.setGreetings(list[3]);
		countrry.setName(list[1]);
		countryDAO.enregistrerCountry(countrry);
		
>>>>>>> origin/aspect-fonctionnel-01
	}
}
