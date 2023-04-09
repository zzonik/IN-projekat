package com.INprojekat.WEB.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

enum Uloga {CITALAC, AUTOR, ADMINISTRATOR};
@Entity
public class Korisnik {
    @Id
    private Long id;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String mail;
    private String lozinka;
    private Date datumRodjenja;
    //private Object profilnaSlika;
    private String opis;
    private Uloga uloga;
}

