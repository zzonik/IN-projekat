package com.INprojekat.WEB.entity;

import jakarta.persistence.*;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

enum Uloga {CITALAC, AUTOR, ADMINISTRATOR};
@Entity
public class Korisnik implements Serializable {
    @Id
    private Long id;
    @Column
    private String ime;
    @Column
    private String prezime;
    @Column
    private String korisnickoIme;
    @Column
    private String mail;
    @Column
    private String lozinka;
    @Column
    private Date datumRodjenja;
    @Lob
    @Column(name = "IMAGE")
    private byte[] profilnaSlika;
    @Column
    private String opis;
    @Column
    private Uloga uloga;

    @OneToMany(mappedBy = "korisnik",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Recenzija> recenzije = new HashSet<>();

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

    public byte[] getProfilnaSlika() { return profilnaSlika; }

    public void setProfilnaSlika(byte[] profilnaSlika) { this.profilnaSlika = profilnaSlika; }

    public String getOpis() { return opis; }

    public void setOpis(String opis) { this.opis = opis; }

    public Uloga getUloga() { return uloga; }

    public void setUloga(Uloga uloga) { this.uloga = uloga; }
}

