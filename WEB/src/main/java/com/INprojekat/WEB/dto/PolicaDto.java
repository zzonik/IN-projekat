package com.INprojekat.WEB.dto;

import com.INprojekat.WEB.entity.Korisnik;
import com.INprojekat.WEB.entity.Polica;
import com.INprojekat.WEB.entity.Recenzija;

import java.util.HashSet;
import java.util.Set;

public class PolicaDto {
    private Long id;
    private String naziv;
    private boolean primarna;
    private Set<Recenzija> Stavka_Police = new HashSet<>();
    private Korisnik korisnik;

    public PolicaDto(Long id, String naziv, boolean primarna, Set<Recenzija> stavka_Police, Korisnik korisnik) {
        this.id = id;
        this.naziv = naziv;
        this.primarna = primarna;
        Stavka_Police = stavka_Police;
        this.korisnik = korisnik;
    }

    public PolicaDto(Polica polica) {
        this.id = polica.getId();
        this.naziv = polica.getNaziv();
        this.primarna = polica.isPrimarna();
        this.Stavka_Police = polica.getStavka_Police();
        this.korisnik = polica.getKorisnik();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public boolean isPrimarna() {
        return primarna;
    }

    public void setPrimarna(boolean primarna) {
        this.primarna = primarna;
    }

    public Set<Recenzija> getStavka_Police() {
        return Stavka_Police;
    }

    public void setStavka_Police(Set<Recenzija> stavka_Police) {
        Stavka_Police = stavka_Police;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }
}