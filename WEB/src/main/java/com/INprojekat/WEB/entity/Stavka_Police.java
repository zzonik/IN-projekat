package com.INprojekat.WEB.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Stavka_Police {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Recenzija recenzija;

    private Knjiga knjiga;

    public Recenzija getRecenzija() { return recenzija; }

    public void setRecenzija(Recenzija recenzija) { this.recenzija = recenzija; }

    public Knjiga getKnjiga() { return knjiga; }

    public void setKnjiga(Knjiga knjiga) { this.knjiga = knjiga; }
}
