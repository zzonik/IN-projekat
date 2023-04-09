package com.INprojekat.WEB.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Zanr{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String naziv;

    public void setNaziv(String zanr) { this.naziv = zanr; }

    public String getNaziv() { return naziv; }
}
