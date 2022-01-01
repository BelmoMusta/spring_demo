package country.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="country")
@Table
public class Country {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	private String code_continent;

	public Country(String name, String code, String devise, String greetings, String codeContinent) {
		super();
		this.name = name;
		this.code = code;
		this.devise = devise;
		this.greetings = greetings;
		this.code_continent = codeContinent;
	}

}
