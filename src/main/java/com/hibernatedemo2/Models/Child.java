package com.hibernatedemo2.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Jacaranda Perez
 * Date: 2021-04-20
 * Project: HibernateDemo2
 */
@Entity
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="countryId", referencedColumnName = "id")
    //@JsonBackReference //the country is not visible when you get a child
    @JsonManagedReference //country visible This reference stops an infinite loop
    //@ColumnDefault("No country")
    private Country country;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name="wishes", joinColumns = {@JoinColumn(referencedColumnName = "id")}, inverseJoinColumns =
            {@JoinColumn(referencedColumnName = "id")})
    private List<Present> whishes;


    public List<Present> getWhishes() {
        return whishes;
    }

    public void setWhishes(List<Present> whishes) {
        this.whishes = whishes;
    }

    public Child(){}

    public Child(String name, Country country) {
        this.name = name;
        this.country = country;
    }

    public Child(String name, Country country, List<Present> whishes) {
        this.name = name;
        this.country = country;
        this.whishes = whishes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
