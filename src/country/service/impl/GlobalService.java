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
		case 1:
			try {
				System.out.println("entre country information :: \"code,name,devise,greeting,code-continent\" :");
				Scanner Ss = new Scanner(System.in);
				String infos = Ss.next();
				services.createCountry(infos);
			}catch(Exception e) {
				System.out.println("please respect the forme:: \\\"code,name,cdevise,greeting,code-continent\\\"\n");						
			}
			break;
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
		case 3:
			try {
				System.out.println("entre the code of country that you want to delete : ");
				Scanner scan = new Scanner(System.in);
				String code = scan.next();
				services.deleteCountry(code);
			}catch(Exception e) {
				System.out.println("plese write the code thre is an exception : !\n"+e);
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
