package country.model;

import javax.annotation.processing.Generated;
import java.io.Serializable;
import java.util.*;


public class Continent implements Serializable {

    private Integer id;
    private String name;
    private String code;
    private Set<Country> countries;

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }

    public void setCountries(Set<Country> countries){ this.countries = countries; }

    public Set<Country> getCountries(){ return countries;}

    }
