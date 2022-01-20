package country.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="country")
@Table
public class Country {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Integer id;
	@Column
	private String name;
	@Column
	private String code;
	@Column
	private String devise;
	@Column
	private String greetings;
	@Column
	private String code_cont;


	public Country() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Country(String name, String code, String devise, String greetings, String code_cont) {
		super();
		//this.id = id;
		this.name = name;
		this.code = code;
		this.devise = devise;
		this.greetings = greetings;
		this.code_cont = code_cont;
	}


	public Country(Integer id,String name, String code, String devise, String greetings, String code_cont) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.devise = devise;
		this.greetings = greetings;
		this.code_cont = code_cont;
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
		return this.greetings;
	}
	
	public void setGreetings(String greetings) {
		this.greetings = greetings;
	}



	public String getCode_cont() {
		return code_cont;
	}



	public void setCode_cont(String code_cont) {
		this.code_cont = code_cont;
	}


	
}
