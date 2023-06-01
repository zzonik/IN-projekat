package com.INprojekat.WEB.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Recenzija implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private int ocena;
    @Column
    private String tekst;
    @Column
    private Date datum;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="korisnik_id")
    private Korisnik korisnik;

    public int getOcena() { return ocena; }

    public void setOcena(int ocena) { this.ocena = ocena; }

    public String getTekst() { return tekst; }

    public void setTekst(String tekst) { this.tekst = tekst; }

    public Date getDatum() { return datum; }

    public void setDatum(Date datum) { this.datum = datum; }

    public Korisnik getKorisnik() { return korisnik; }

    public void setKorisnik(Korisnik korisnik) { this.korisnik = korisnik; }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
