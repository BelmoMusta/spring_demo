package country.model;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Continent")
public class Continent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idContinent;

    @Column(name = "nameContinent")
    private String nameContinent;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idContinent")
    private Set<Country> countries;

    public Continent() {
    }

    public Continent(String nameContinent) {

        this.nameContinent = nameContinent;
    }

    public int getId() {
        return idContinent;
    }

    public String getName() {
        return nameContinent;
    }

    public void setName(String nameContinent) {
        this.nameContinent = nameContinent;
    }

    public Set<Country> getCountries() {
        return countries;
    }

    public void setCountries(Set<Country> countries) {
        this.countries = countries;
    }

    @Override
    public String toString() {
        return "Continent{" + "id=" + idContinent + ", name="
                + nameContinent + ", countries=" + countries + '}';
    }
}
