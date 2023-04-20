package com.INprojekat.WEB.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Polica implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String naziv;
    @Column
    private boolean primarna;
    @OneToMany
    private Set<Recenzija> Stavka_Police = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="korisnik_id")
    private Korisnik korisnik;

    public String getNaziv() { return naziv; }

    public void setNaziv(String naziv) { naziv = naziv; }

    public boolean isPrimarna() { return primarna; }

    public void setPrimarna(boolean primarna) { this.primarna = primarna; }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Set<Recenzija> getStavka_Police() { return Stavka_Police; }

    public void setStavka_Police(Set<Recenzija> stavka_Police) { Stavka_Police = stavka_Police; }
}
