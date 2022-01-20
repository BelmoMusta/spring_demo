package country.service.impl;

import country.dao.CountryDAO;
import country.model.Country;
import country.service.IServiceWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceWorkerImpl implements IServiceWorker {
	@Autowired
	private CountryDAO countryDAO;
	
	@Override
	public void addCountry(String country) {
		// TODO Auto-generated method stub
		String[] infoListe=country.split(",");
		Country countryToAdd=new Country();
		countryToAdd.setCode(infoListe[0]);
		countryToAdd.setName(infoListe[1]);
		countryToAdd.setDevise(infoListe[2]);
		countryToAdd.setGreetings(infoListe[3]);
		countryDAO.addCountry(countryToAdd);
	}

	@Override
	public void displayCountry() {
		// TODO Auto-generated method stub
		countryDAO.displayCountry();
	}
	
	@Override
	public void displayByCode(String code) {
		countryDAO.displayByCode(code);
	}
	

	
}