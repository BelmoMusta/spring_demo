package country.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import continent.model.Continent;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Country {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
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
    @JoinColumn(name="CountinentId")
	private Continent continent;

}