package com.INprojekat.WEB.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ZAHTEV")
public class ZahtevZaAktivacijuNalogaAutora implements Serializable {
    public enum Status {na_cekanju, odobren, odbijen}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String email;
    @Column
    private String telefon;
    @Column
    private String poruka;
    @Column
    private Date datum;
    @Column
    private Status status;

    @OneToOne
    private Korisnik korisnik;

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getTelefon() { return telefon; }

    public void setTelefon(String telefon) { this.telefon = telefon; }

    public String getPoruka() { return poruka; }

    public void setPoruka(String poruka) { this.poruka = poruka; }

    public Date getDatum() { return datum; }

    public void setDatum(Date datum) { this.datum = datum; }

    public Status getStatus() { return status; }

    public void setStatus(Status status) { this.status = status; }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }
}
