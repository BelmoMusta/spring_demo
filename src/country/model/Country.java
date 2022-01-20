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

}