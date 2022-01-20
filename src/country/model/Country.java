package country.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@Entity
public class Country {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String code;
	private String devise;
	private String greetings;
	@ManyToOne
	private Continent continent;

	

	@Override
	public boolean equals(Object o) {
		if (o instanceof Country) {
			Country c = (Country) o;
			if (this.code.equals(c.code) && this.name.equals(c.name) && this.devise.equals(c.devise)
					&& this.greetings.equals(c.greetings) && this.continent.getId() == c.continent.getId())
				return true;
		}
		return false;
	}
}
