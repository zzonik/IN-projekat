package com.INprojekat.WEB.dto;

import java.util.Date;

public class AutorRegisterDto {
    private String ime;
    private String prezime;
    private String korisnickoIme;

    private String mail;

    private String lozinka;
    private String profilnaSlika;

    public AutorRegisterDto(String ime, String prezime, String korisnickoIme, String mail, String lozinka, String profilnaSlika) {
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.mail = mail;
        this.lozinka = lozinka;
        this.profilnaSlika = profilnaSlika;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public AutorRegisterDto() {
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

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getProfilnaSlika() {
        return profilnaSlika;
    }

    public void setProfilnaSlika(String profilnaSlika) {
        this.profilnaSlika = profilnaSlika;
    }
}
