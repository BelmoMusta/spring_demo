package country;

import country.service.Country;

public class CountryService {
	private Country country;
	
	public CountryService() {
	
	}
	
	// injection par constructeur
	public CountryService(Country country) {
		this.country = country;
	}
	
	public String welcome() {
		return country.greet();
	}
	
	public void setCountry(Country country) {
		this.country = country;
	}
}
