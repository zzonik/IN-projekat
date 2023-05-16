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
    private Uloga uloga;
    private Set<Recenzija> recenzije = new HashSet<>();
    private Set<Polica> police = new HashSet<>();
    private Set<ZahtevZaAktivacijuNalogaAutora> Zahtevi_za_aktivaciju = new HashSet<>();

    public KorisnikDto(Long id, String ime, String prezime, Date datumRodjenja, String profilnaSlika, String opis, Uloga uloga, Set<Recenzija> recenzije, Set<Polica> police, Set<ZahtevZaAktivacijuNalogaAutora> zahtevi_za_aktivaciju) {
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
        this.Zahtevi_za_aktivaciju = korisnik.getZahtevi_za_aktivaciju();    }
}
