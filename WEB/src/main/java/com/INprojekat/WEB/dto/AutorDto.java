package com.INprojekat.WEB.dto;

import com.INprojekat.WEB.entity.Autor;
import com.INprojekat.WEB.entity.Knjiga;
import com.INprojekat.WEB.entity.Korisnik;
import jakarta.persistence.Column;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class AutorDto {
    private boolean aktivnost;
    private Set<Knjiga> knjige = new HashSet<>();

    private String ime;

    private String prezime;
    private String korisnickoIme;

    private String mail;

    private String lozinka;

    private Date datumRodjenja;

    private String profilnaSlika;

    private String opis;

    private Korisnik.Uloga uloga;

    public AutorDto(boolean aktivnost, String ime, String prezime, String profilnaSlika) {
        this.aktivnost = aktivnost;
        this.ime = ime;
        this.prezime = prezime;
        this.profilnaSlika = profilnaSlika;
    }

    public AutorDto(boolean aktivnost) {
        this.aktivnost = aktivnost;
    }

    public AutorDto(Autor autor) {
        this.aktivnost = autor.getAktivnost();
    }
    public AutorDto() {
    }

    public boolean isAktivnost() {
        return aktivnost;
    }

    public void setAktivnost(boolean aktivnost) {
        this.aktivnost = aktivnost;
    }

    public Set<Knjiga> getKnjige() {
        return knjige;
    }

    public void setKnjige(Set<Knjiga> knjige) {
        this.knjige = knjige;
    }
}
