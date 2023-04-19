package com.INprojekat.WEB.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Knjiga implements Serializable {
    @Id
    private Long id;
    @Column
    private String naslov;
    @Column(name = "IMAGE")
    private String naslovnaFotografija;
    @Column
    private String ISBN;
    @Column
    private Date datumObjavljivanja;
    @Column
    private int brojStrana;
    @Column
    private String opis;
    @Column
    private Double ocena;
    @ManyToMany
    @JoinTable(name = "knjiga-zanr",
            joinColumns = @JoinColumn(name = "knjiga_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "zanr_id", referencedColumnName = "id"))
    private Set<Zanr> zanrovi = new HashSet<>();

    @OneToMany(mappedBy = "knjiga")
    private Set<StavkaPolice> stavka_polica = new HashSet<>();

    @OneToMany(mappedBy = "knjiga")
    private Set<Recenzija> recenzije = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "knjiga-autor",
            joinColumns = @JoinColumn(name = "knjiga_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id", referencedColumnName = "id"))
    private Set<Zanr> autori = new HashSet<>();

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getNaslov() { return naslov; }

    public void setNaslov(String naslov) { this.naslov = naslov; }

    public String getNaslovnaFotografija() { return naslovnaFotografija; }

    public void setNaslovnaFotografija(String naslovnaFotografija) { this.naslovnaFotografija = naslovnaFotografija; }

    public String getISBN() { return ISBN; }

    public void setISBN(String ISBN) { this.ISBN = ISBN; }

    public Date getDatumObjavljivanja() { return datumObjavljivanja; }

    public void setDatumObjavljivanja(Date datumObjavljivanja) { this.datumObjavljivanja = datumObjavljivanja; }

    public int getBrojStrana() { return brojStrana; }

    public void setBrojStrana(int brojStrana) { this.brojStrana = brojStrana; }

    public String getOpis() { return opis; }

    public void setOpis(String opis) { this.opis = opis; }

    public Set<Zanr> getZanrovi() { return zanrovi; }

    public void setZanrovi(Set<Zanr> zanrovi) { this.zanrovi = zanrovi; }

    public Double getOcena() { return ocena; }

    public void setOcena(Double ocena) { this.ocena = ocena; }

    public Set<StavkaPolice> getStavka_polica() { return stavka_polica; }

    public void setStavka_polica(Set<StavkaPolice> stavka_polica) { this.stavka_polica = stavka_polica; }

    public Set<Recenzija> getRecenzije() { return recenzije; }

    public void setRecenzije(Set<Recenzija> recenzije) { this.recenzije = recenzije; }

    public Set<Zanr> getAutori() { return autori; }

    public void setAutori(Set<Zanr> autori) { this.autori = autori; }
}
