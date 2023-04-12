package com.INprojekat.WEB.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class StavkaPolice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Recenzija recenzija;

    @ManyToOne
    private Knjiga knjiga;

    @ManyToOne
    private Polica polica;

    public Recenzija getRecenzija() { return recenzija; }

    public void setRecenzija(Recenzija recenzija) { this.recenzija = recenzija; }

    public Knjiga getKnjiga() { return knjiga; }

    public void setKnjiga(Knjiga knjiga) { this.knjiga = knjiga; }
}
