package country.service.impl;

//import country.dao.ContinentDAO;
import country.dao.CountryDAO;
import country.model.Country;

import country.service.IServiceWorker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class ServiceWorkerImpl implements IServiceWorker {

	@Autowired
	private CountryDAO cDAO;
	
	/*@Autowired
	private ContinentDAO conDAO;
	*/
	@Override
	public void listCountry()
	{
		cDAO.listCountry();	
	}
	@Override
	public void AddCountry(String country) {
		String[] list=country.split(",");
		Country Ccountry=new Country();
		Ccountry.setCode(list[0]);
		Ccountry.setDevise(list[2]);
		Ccountry.setGreetings(list[3]);
		Ccountry.setName(list[1]);
		//String continentId = list[4];
		//country.setContinent(conDAO.getContinentByCode(continentId));

		cDAO.saveCountry(Ccountry);

	}
	
	@Override
	public void findByCode(String code) {
			cDAO.findByCode(code);
	}
	
	@Override
	public void deleteByCode(String code)
	{
		cDAO.deleteByCode(code);
	}
	
	@Override
	public void updateByCode(String code, String country) {
		String[] list=country.split(",");
		Country countrry=new Country();
		countrry.setName(list[0]);
		countrry.setDevise(list[1]);
		countrry.setGreetings(list[2]);
		//country.setContinent(conDAO.getContinentByCode(list[4]));
		cDAO.updateByCode(code,countrry);
	
	}
  
	/*@Override
	public List<Country> getCountriesByContinent(String conCode) {

		for( Country country : cDAO.getCountriesByContinent(conCode)){
			System.out.println(country.toString());
		}
		return cDAO.getCountriesByContinent(conCode);
	}*/

	
}