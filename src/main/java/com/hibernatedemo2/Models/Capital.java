package com.hibernatedemo2.Models;

import javax.persistence.*;

@Entity
public class Capital {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    public Capital(){}

    public Capital(String name) {
        this.name = name;
    }

    public Capital(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
    }

    public String getName() {
        return name;
    }
}
