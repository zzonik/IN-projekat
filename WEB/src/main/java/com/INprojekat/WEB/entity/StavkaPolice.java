package com.INprojekat.WEB.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "STAVKE")
public class StavkaPolice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="recenzija_id")
    private Recenzija recenzija;

    @ManyToOne
    @JoinColumn(name="knjiga_id")
    private Knjiga knjiga;

    @ManyToOne
    @JoinColumn(name="polica_id")
    private Polica polica;

    public Recenzija getRecenzija() { return recenzija; }

    public void setRecenzija(Recenzija recenzija) { this.recenzija = recenzija; }

    public Knjiga getKnjiga() { return knjiga; }

    public void setKnjiga(Knjiga knjiga) { this.knjiga = knjiga; }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Polica getPolica() {
        return polica;
    }

    public void setPolica(Polica polica) {
        this.polica = polica;
    }
}
