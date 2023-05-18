package com.INprojekat.WEB.entity;

import jakarta.persistence.*;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED )
public class Korisnik {
    public enum Uloga {CITALAC, AUTOR, ADMINISTRATOR};
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String ime;
    @Column
    private String prezime;
    @Column(name="username")
    private String korisnickoIme;
    @Column
    private String mail;
    @Column
    private String lozinka;
    @Column(name = "date")
    private Date datumRodjenja;
    @Column(name = "IMAGE")
    private String profilnaSlika;
    @Column
    private String opis;
    @Column
    private Uloga uloga;

    @OneToMany(mappedBy = "korisnik",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Polica> police = new HashSet<>();

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

    public String getProfilnaSlika() { return profilnaSlika; }

    public void setProfilnaSlika(String profilnaSlika) { this.profilnaSlika = profilnaSlika; }

    public String getOpis() { return opis; }

    public void setOpis(String opis) { this.opis = opis; }

    public Uloga getUloga() { return uloga; }

    public void setUloga(Uloga uloga) { this.uloga = uloga; }

    public Set<Polica> getPolice() {
        return police;
    }

    public void setPolice(Set<Polica> police) {
        this.police = police;
    }

}

