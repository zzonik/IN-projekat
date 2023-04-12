package com.INprojekat.WEB.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Zanr implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String naziv;

    @ManyToMany(mappedBy = "zanrovi")
    private Set<Knjiga> knjige = new HashSet<>();

    public void setNaziv(String zanr) { this.naziv = zanr; }

    public String getNaziv() { return naziv; }
}
