package country.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "Continent")
public class Continent implements Serializable {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer id;

    @Column(name ="name")
    private String name;

    @Column(name ="code")
    private String code;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="CONTINENT_ID")
    private Set<Country> countries;

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

    public Set<Country> getCountries() {
        return countries;
    }

    public void setCountries(Set<Country> countries) {
        this.countries = countries;
    }
}
