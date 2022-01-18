package country.model;

import javax.persistence.*;

//import org.hibernate.annotations.*;

@Entity
@Table(name="country")
public class Country {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="name",nullable=false)
	private String name;
	
	@Column(name="code",nullable=false)
	private String code;
	
	@Column(name="devise",nullable=false)
	private String devise;
	
	@Column(name="greetings",nullable=false)
	private String greetings;
	
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
