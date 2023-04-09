package com.INprojekat.WEB.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Zanr{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String naziv;

    @ManyToMany(mappedBy = "zanrovi")
    private Set<Zanr> zanr = new HashSet<>();

    public void setNaziv(String zanr) { this.naziv = zanr; }

    public String getNaziv() { return naziv; }
}
