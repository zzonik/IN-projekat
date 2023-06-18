package com.INprojekat.WEB.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "recenzija", cascade = CascadeType.REMOVE)
    private Set<StavkaPolice> stavkePolice = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="knjiga_id")
    private Knjiga knjiga;

    public int getOcena() { return ocena; }

    public void setOcena(int ocena) { this.ocena = ocena; }

    public String getTekst() { return tekst; }

    public void setTekst(String tekst) { this.tekst = tekst; }

    public Date getDatum() { return datum; }

    public void setDatum(Date datum) { this.datum = datum; }

    public Korisnik getKorisnik() { return korisnik; }

    public void setKorisnik(Korisnik korisnik) { this.korisnik = korisnik; }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Knjiga getKnjiga() { return knjiga; }

    public void setKnjiga(Knjiga knjiga) { this.knjiga = knjiga; }

    public Set<StavkaPolice> getStavkePolice() { return stavkePolice; }

    public void setStavkePolice(Set<StavkaPolice> stavkePolice) { this.stavkePolice = stavkePolice; }
}
