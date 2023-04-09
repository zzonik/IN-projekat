package com.INprojekat.WEB.entity;

import jakarta.persistence.*;

@Entity
public class Recenzija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int ocena;
    private String tekst;
    private String datum;
    @ManyToOne
    private Korisnik korisnik;

    public int getOcena() { return ocena; }

    public void setOcena(int ocena) { this.ocena = ocena; }

    public String getTekst() { return tekst; }

    public void setTekst(String tekst) { this.tekst = tekst; }

    public String getDatum() { return datum; }

    public void setDatum(String datum) { this.datum = datum; }

    public Korisnik getKorisnik() { return korisnik; }

    public void setKorisnik(Korisnik korisnik) { this.korisnik = korisnik; }
}
