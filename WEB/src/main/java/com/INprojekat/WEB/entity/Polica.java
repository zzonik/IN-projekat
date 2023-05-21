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
    private Set<StavkaPolice> stavkePolica = new HashSet<>();

    public Polica(Long id, String naziv, boolean primarna, Set<StavkaPolice> stavkePolica) {
        this.id = id;
        this.naziv = naziv;
        this.primarna = primarna;
        this.stavkePolica = stavkePolica;
    }

    public Polica(String naziv, boolean primarna) {
        this.naziv = naziv;
        this.primarna = primarna;
    }

    public Polica() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public boolean isPrimarna() {
        return primarna;
    }

    public void setPrimarna(boolean primarna) {
        this.primarna = primarna;
    }

    public Set<StavkaPolice> getStavkePolica() {
        return stavkePolica;
    }

    public void setStavkePolica(Set<StavkaPolice> stavkePolica) {
        this.stavkePolica = stavkePolica;
    }
}
