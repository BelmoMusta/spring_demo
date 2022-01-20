package country.service.impl;

import country.domain.Country;
import country.repository.ICountryDao;
import country.service.ICountryService;
import country.service.IServiceDetail;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class ServiceDeatails implements IServiceDetail {
	@Autowired
	private ICountryDao countryDao;
	@Autowired
	private ApplicationContext applicationContext;
	
	@Override
	public void afficheCountry(String code) {
		Country pays = countryDao.getByCode(code);
		// car c'est prototype
		ICountryService countryService = applicationContext.getBean(ICountryService.class, pays);
		
		System.out.println("COUNTRY : "+ pays.getName()+"\n WELCOME : " + countryService.welcome()+"\n Devise is :" + countryService.devise());
		
	}

	
}
