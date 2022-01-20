package country.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import net.bytebuddy.dynamic.loading.ClassReloadingStrategy.Strategy;
import lombok.Data;

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
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (devise == null) {
			if (other.devise != null)
				return false;
		} else if (!devise.equals(other.devise))
			return false;
		if (greetings == null) {
			if (other.greetings != null)
				return false;
		} else if (!greetings.equals(other.greetings))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((devise == null) ? 0 : devise.hashCode());
		result = prime * result + ((greetings == null) ? 0 : greetings.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

}