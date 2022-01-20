package country.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="continent")
@Table
public class Continent {
	
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String name;
	@Column
	private String codeContinent;
	
	
	
	public Continent() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Continent(String name, String codeContinent) {
		super();
		//this.id = id;
		this.name = name;
		this.codeContinent = codeContinent;
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
	
}
