package com.INprojekat.WEB.dto;

import com.INprojekat.WEB.entity.Knjiga;
import com.INprojekat.WEB.entity.Zanr;
import jakarta.persistence.Column;

import java.util.HashSet;
import java.util.Set;

public class ZanrDto {
    private Long id;
    private String naziv;
    private Set<Knjiga> knjige = new HashSet<>();

    public ZanrDto(Long id, String naziv, Set<Knjiga> knjige) {
        this.id = id;
        this.naziv = naziv;
        this.knjige = knjige;
    }
    public ZanrDto(Zanr zanr) {
        this.id = zanr.getId();
        this.naziv = zanr.getNaziv();
        this.knjige = zanr.getKnjige();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Set<Knjiga> getKnjige() {
        return knjige;
    }

    public void setKnjige(Set<Knjiga> knjige) {
        this.knjige = knjige;
    }
}
