package com.INprojekat.WEB.entity;

public class Polica {
    private String Naziv;
    private boolean primarna;
    private Stavka_Police s_police;

    public String getNaziv() { return Naziv; }

    public void setNaziv(String naziv) { Naziv = naziv; }

    public boolean isPrimarna() { return primarna; }

    public void setPrimarna(boolean primarna) { this.primarna = primarna; }

    public Stavka_Police getS_police() { return s_police; }

    public void setS_police(Stavka_Police s_police) { this.s_police = s_police; }
}
