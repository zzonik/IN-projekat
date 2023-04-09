package com.INprojekat.WEB.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.awt.image.BufferedImage;
import java.util.Date;

enum Uloga {CITALAC, AUTOR, ADMINISTRATOR};
@Entity
public class Korisnik {
    @Id
    private Long id;
    @Column
    private String ime;
    @Column
    private String prezime;
    @Column
    private String korisnickoIme;
    private String mail;
    private String lozinka;
    private Date datumRodjenja;
    private Object profilnaSlika; // potrebna doradnja
    private String opis;
    private Uloga uloga;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getIme() { return ime; }

    public void setIme(String ime) { this.ime = ime; }

    public String getPrezime() { return prezime; }

    public void setPrezime(String prezime) { this.prezime = prezime; }

    public String getKorisnickoIme() { return korisnickoIme; }

    public void setKorisnickoIme(String korisnickoIme) { this.korisnickoIme = korisnickoIme; }

    public String getMail() { return mail; }

    public void setMail(String mail) { this.mail = mail; }

    public String getLozinka() { return lozinka; }

    public void setLozinka(String lozinka) { this.lozinka = lozinka; }

    public Date getDatumRodjenja() { return datumRodjenja; }

    public void setDatumRodjenja(Date datumRodjenja) { this.datumRodjenja = datumRodjenja; }

    public Object getProfilnaSlika() { return profilnaSlika; }

    public void setProfilnaSlika(Object profilnaSlika) { this.profilnaSlika = profilnaSlika; }

    public String getOpis() { return opis; }

    public void setOpis(String opis) { this.opis = opis; }

    public Uloga getUloga() { return uloga; }

    public void setUloga(Uloga uloga) { this.uloga = uloga; }
}

