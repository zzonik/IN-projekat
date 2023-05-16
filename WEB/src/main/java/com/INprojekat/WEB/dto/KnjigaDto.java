package com.INprojekat.WEB.dto;

import com.INprojekat.WEB.entity.Knjiga;
import com.INprojekat.WEB.entity.Recenzija;
import com.INprojekat.WEB.entity.StavkaPolice;
import com.INprojekat.WEB.entity.Zanr;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class KnjigaDto {
    private Long id;
    private String naslov;
    private String naslovnaFotografija;
    private String ISBN;
    private int brojStrana;
    private Date datumObjavljivanja;
    private String opis;
    private Double ocena;
    private Set<Zanr> zanrovi = new HashSet<>();
    private Set<StavkaPolice> stavka_polica = new HashSet<>();
    private Set<Recenzija> recenzije = new HashSet<>();
    private Set<Zanr> autori = new HashSet<>();

    public KnjigaDto(Long id, String naslov, String naslovnaFotografija, String ISBN, Date datumObjavljivanja, int brojStrana, String opis, Double ocena, Set<Zanr> zanrovi, Set<StavkaPolice> stavka_polica, Set<Recenzija> recenzije, Set<Zanr> autori) {
        this.id = id;
        this.naslov = naslov;
        this.naslovnaFotografija = naslovnaFotografija;
        this.ISBN = ISBN;
        this.datumObjavljivanja = datumObjavljivanja;
        this.brojStrana = brojStrana;
        this.opis = opis;
        this.ocena = ocena;
        this.zanrovi = zanrovi;
        this.stavka_polica = stavka_polica;
        this.recenzije = recenzije;
        this.autori = autori;
    }

    public KnjigaDto(Knjiga knjiga) {
        this.id = knjiga.getId();
        this.naslov = knjiga.getNaslov();
        this.naslovnaFotografija = knjiga.getNaslovnaFotografija();
        this.ISBN = knjiga.getISBN();
        this.datumObjavljivanja = knjiga.getDatumObjavljivanja();
        this.brojStrana = knjiga.getBrojStrana();
        this.opis = knjiga.getOpis();
        this.ocena = knjiga.getOcena();
        this.zanrovi = knjiga.getZanrovi();
        this.stavka_polica = knjiga.getStavka_polica();
        this.recenzije = knjiga.getRecenzije();
        this.autori = knjiga.getAutori();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getNaslovnaFotografija() {
        return naslovnaFotografija;
    }

    public void setNaslovnaFotografija(String naslovnaFotografija) {
        this.naslovnaFotografija = naslovnaFotografija;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getBrojStrana() {
        return brojStrana;
    }

    public void setBrojStrana(int brojStrana) {
        this.brojStrana = brojStrana;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Double getOcena() {
        return ocena;
    }

    public void setOcena(Double ocena) {
        this.ocena = ocena;
    }

    public Set<Zanr> getZanrovi() {
        return zanrovi;
    }

    public void setZanrovi(Set<Zanr> zanrovi) {
        this.zanrovi = zanrovi;
    }

    public Set<StavkaPolice> getStavka_polica() {
        return stavka_polica;
    }

    public void setStavka_polica(Set<StavkaPolice> stavka_polica) {
        this.stavka_polica = stavka_polica;
    }

    public Set<Recenzija> getRecenzije() {
        return recenzije;
    }

    public void setRecenzije(Set<Recenzija> recenzije) {
        this.recenzije = recenzije;
    }

    public Set<Zanr> getAutori() {
        return autori;
    }

    public void setAutori(Set<Zanr> autori) {
        this.autori = autori;
    }
}
