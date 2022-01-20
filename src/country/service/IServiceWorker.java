package country.service;

import org.springframework.stereotype.Service;

@Service
public interface IServiceWorker {
	void dealWithMenuChoice(String choix);
	void showMenu();
}
