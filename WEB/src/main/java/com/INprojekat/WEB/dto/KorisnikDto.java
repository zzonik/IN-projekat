package com.INprojekat.WEB.dto;

import com.INprojekat.WEB.entity.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class KorisnikDto {
    private Long id;
    private String ime;
    private String prezime;
    private Date datumRodjenja;
    private String profilnaSlika;
    private String opis;
    private Korisnik.Uloga uloga;
    private Set<Recenzija> recenzije = new HashSet<>();
    private Set<Polica> police = new HashSet<>();
    private Set<ZahtevZaAktivacijuNalogaAutora> Zahtevi_za_aktivaciju = new HashSet<>();

    public KorisnikDto(Long id, String ime, String prezime, Date datumRodjenja, String profilnaSlika, String opis, Korisnik.Uloga uloga, Set<Recenzija> recenzije, Set<Polica> police, Set<ZahtevZaAktivacijuNalogaAutora> zahtevi_za_aktivaciju) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.profilnaSlika = profilnaSlika;
        this.opis = opis;
        this.uloga = uloga;
        this.recenzije = recenzije;
        this.police = police;
        this.Zahtevi_za_aktivaciju = zahtevi_za_aktivaciju;
    }

    public KorisnikDto(Korisnik korisnik) {
        this.id = korisnik.getId();
        this.ime = korisnik.getIme();
        this.prezime = korisnik.getPrezime();
        this.datumRodjenja = korisnik.getDatumRodjenja();
        this.profilnaSlika = korisnik.getProfilnaSlika();
        this.opis = korisnik.getOpis();
        this.uloga = korisnik.getUloga();
        this.recenzije = korisnik.getRecenzije();
        this.police = korisnik.getPolice();
        this.Zahtevi_za_aktivaciju = korisnik.getZahtevi_za_aktivaciju();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getProfilnaSlika() {
        return profilnaSlika;
    }

    public void setProfilnaSlika(String profilnaSlika) {
        this.profilnaSlika = profilnaSlika;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Korisnik.Uloga getUloga() {
        return uloga;
    }

    public void setUloga(Korisnik.Uloga uloga) {
        this.uloga = uloga;
    }

    public Set<Recenzija> getRecenzije() {
        return recenzije;
    }

    public void setRecenzije(Set<Recenzija> recenzije) {
        this.recenzije = recenzije;
    }

    public Set<Polica> getPolice() {
        return police;
    }

    public void setPolice(Set<Polica> police) {
        this.police = police;
    }

    public Set<ZahtevZaAktivacijuNalogaAutora> getZahtevi_za_aktivaciju() {
        return Zahtevi_za_aktivaciju;
    }

    public void setZahtevi_za_aktivaciju(Set<ZahtevZaAktivacijuNalogaAutora> zahtevi_za_aktivaciju) {
        Zahtevi_za_aktivaciju = zahtevi_za_aktivaciju;
    }
}
