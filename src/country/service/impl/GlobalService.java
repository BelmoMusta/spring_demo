package country.service.impl;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import country.service.IGlobalService;
import country.service.IServiceDetail;
@Service
public class GlobalService implements IGlobalService{
    @Autowired
    private IServiceDetail services;
	@Override
	public void traitement(String inputIndex) {
		try {
		int index=Integer.parseInt(inputIndex);
		switch(index) {
		
		case 2:
			try {
				System.out.print("entre the code : ");
				Scanner scan = new Scanner(System.in);
				String language = scan.next();
				services.afficheCountry(language);
			}catch(Exception e) {
				System.out.println("plese write the code exception : !\n"+e);
			}
			break;
		
		default:
			System.out.println("");
	}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

}
