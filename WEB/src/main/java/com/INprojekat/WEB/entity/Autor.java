package com.INprojekat.WEB.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Autor implements Serializable {
    @Id
    private Long id;
    @Column
    private boolean aktivnost;
    @ManyToMany(mappedBy = "autori")
    private Set<Knjiga> knjige = new HashSet<>();

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public boolean isAktivnost() { return aktivnost; }

    public void setAktivnost(boolean aktivnost) { this.aktivnost = aktivnost; }

    public Set<Knjiga> getKnjige() { return knjige; }

    public void setKnjige(Set<Knjiga> knjige) { this.knjige = knjige; }
}
