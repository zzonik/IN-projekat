package com.INprojekat.WEB.dto;

import com.INprojekat.WEB.entity.Autor;
import com.INprojekat.WEB.entity.Knjiga;

import java.util.HashSet;
import java.util.Set;

public class AutorDto {
    private boolean aktivnost;
    private Set<Knjiga> knjige = new HashSet<>();

    public AutorDto(boolean aktivnost, Set<Knjiga> knjige) {
        this.aktivnost = aktivnost;
        this.knjige = knjige;
    }

    public AutorDto(Autor autor) {
        this.aktivnost = autor.getAktivnost();
        this.knjige = autor.getKnjige();
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
