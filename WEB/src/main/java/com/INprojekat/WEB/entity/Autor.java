package com.INprojekat.WEB.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Autor extends Korisnik {

    @Column
    private boolean aktivnost;

    @OneToMany(mappedBy = "autori")
    private Set<Knjiga> knjige = new HashSet<>();

    public boolean getAktivnost() { return aktivnost; }

    public void setAktivnost(boolean aktivnost) { this.aktivnost = aktivnost; }

    public Set<Knjiga> getKnjige() { return knjige; }

    public void setKnjige(Set<Knjiga> knjige) { this.knjige = knjige; }
}
