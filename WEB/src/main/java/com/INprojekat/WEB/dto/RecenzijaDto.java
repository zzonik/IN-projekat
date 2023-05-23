package com.INprojekat.WEB.dto;

import com.INprojekat.WEB.entity.Knjiga;
import com.INprojekat.WEB.entity.Korisnik;
import com.INprojekat.WEB.entity.Recenzija;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Date;

public class RecenzijaDto {
    private Long id;
    private int ocena;
    private String tekst;
    private Date datum;
    private Long korisnikId;

    public RecenzijaDto() {
    }

    public RecenzijaDto(Long id, int ocena, String tekst, Date datum, Long korisnikId) {
        this.id = id;
        this.ocena = ocena;
        this.tekst = tekst;
        this.datum = datum;
        this.korisnikId = korisnikId;
    }

    public RecenzijaDto(Recenzija recenzija) {
        this.id = recenzija.getId();
        this.ocena = recenzija.getOcena();
        this.tekst = recenzija.getTekst();
        this.datum = recenzija.getDatum();
        this.korisnikId = recenzija.getKorisnik().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Long getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Long korisnikId) {
        this.korisnikId = korisnikId;
    }
}
