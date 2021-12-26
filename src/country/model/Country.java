package country.model;

import lombok.Data;

@Data
public class Country {
	private Integer id;
	private String name;
	private String code;
	private String devise;
	private String greetings;
	
}
