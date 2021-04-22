package com.hibernatedemo2.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Country {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;
  //name= vad foreign ska kallas, vad columnen heter i capital table.
    @JoinColumn(name="capitalId", referencedColumnName ="id")
    @OneToOne(cascade = CascadeType.ALL)
    private Capital capital;
    @OneToMany(mappedBy = "country")
    //@JsonManagedReference
    @JsonBackReference //children not visible
    private List<Child> children;

    public Country() {}

    public Country(String name, Capital capital) {
        this.name = name;
        this.capital = capital;
    }

    public Country(Long id, String name, Capital capital) {
        this.id = id;
        this.name = name;
        this.capital = capital;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Capital getCapital() {
        return capital;
    }

    public void setCapital(Capital capital) {
        this.capital = capital;
    }
}
