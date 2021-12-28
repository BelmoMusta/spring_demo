package country.model;

public class Country {
	private Integer id;
	private String name;
	private String code;
	private String devise;
	private String greetings;
	
	
	public Country() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Country(String name, String code, String devise, String greetings) {
		super();
		this.name = name;
		this.code = code;
		this.devise = devise;
		this.greetings = greetings;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getDevise() {
		return devise;
	}
	
	public void setDevise(String devise) {
		this.devise = devise;
	}
	
	public String getGreetings() {
		return greetings;
	}
	
	public void setGreetings(String greetings) {
		this.greetings = greetings;
	}
}
