package com.INprojekat.WEB.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Knjiga implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String naslov;
    @Column(name = "IMAGE")
    private String naslovnaFotografija;
    @Column
    private String ISBN;
    @Column(name = "date")
    private Date datumObjavljivanja;
    @Column(name = "pages")
    private int brojStrana;
    @Column
    private String opis;
    @Column
    private Double ocena;
    @OneToOne
    private Zanr zanr;

    @OneToMany(mappedBy = "knjiga")
    private Set<StavkaPolice> stavka_polica = new HashSet<>();

    @OneToMany
    private Set<Recenzija> recenzije = new HashSet<>();

    @ManyToOne
    private Autor autor;

    public Knjiga(String naslov, String naslovnaFotografija, String ISBN, int brojStrana, String opis) {
        this.naslov = naslov;
        this.naslovnaFotografija = naslovnaFotografija;
        this.ISBN = ISBN;
        this.brojStrana = brojStrana;
        this.opis = opis;
    }
    public Knjiga() {
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getNaslov() { return naslov; }

    public void setNaslov(String naslov) { this.naslov = naslov; }

    public String getNaslovnaFotografija() { return naslovnaFotografija; }

    public void setNaslovnaFotografija(String naslovnaFotografija) { this.naslovnaFotografija = naslovnaFotografija; }

    public String getISBN() { return ISBN; }

    public void setISBN(String ISBN) { this.ISBN = ISBN; }

    public Date getDatumObjavljivanja() { return datumObjavljivanja; }

    public void setDatumObjavljivanja(Date datumObjavljivanja) { this.datumObjavljivanja = datumObjavljivanja; }

    public int getBrojStrana() { return brojStrana; }

    public void setBrojStrana(int brojStrana) { this.brojStrana = brojStrana; }

    public String getOpis() { return opis; }

    public void setOpis(String opis) { this.opis = opis; }

    public Zanr getZanr() {
        return zanr;
    }

    public void setZanr(Zanr zanr) {
        this.zanr = zanr;
    }

    public Double getOcena() { return ocena; }

    public void setOcena(Double ocena) { this.ocena = ocena; }

    public Set<StavkaPolice> getStavka_polica() { return stavka_polica; }

    public void setStavka_polica(Set<StavkaPolice> stavka_polica) { this.stavka_polica = stavka_polica; }

    public Set<Recenzija> getRecenzije() { return recenzije; }

    public void setRecenzije(Set<Recenzija> recenzije) { this.recenzije = recenzije; }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}
