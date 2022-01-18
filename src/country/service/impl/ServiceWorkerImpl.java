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
		
	}


	@Override
	public void findByCode(String code) {
			countryDAO.findByCode(code);
		
		
	}
}
