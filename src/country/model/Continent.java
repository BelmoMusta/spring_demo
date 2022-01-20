package country.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "CONTINENT")
public class Continent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    String code;

    @OneToMany(mappedBy="continent")
    private Set<Country> countries;

    public Continent() {
    }

    public Continent(String code, String name) {
        this.name = name;
        this.code = code;
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

    public Set<Country> getCountries() {
        return countries;
    }

    public void setCountries(Set<Country> countries) {
        this.countries = countries;
    }
}
