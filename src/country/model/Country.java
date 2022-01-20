package country.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Country")
@Data
public class Country {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name ="name")
	private String name;
	@Column(name ="code")
	private String code;
	@Column(name ="devise")
	private String devise;
	@Column(name ="greetings")
	private String greetings;
  
	@ManyToOne
	private Continent continent;
}
