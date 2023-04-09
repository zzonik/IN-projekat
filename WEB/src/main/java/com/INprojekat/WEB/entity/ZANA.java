package com.INprojekat.WEB.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

enum Status {na_cekanju,odobren,odbijen}
@Entity
public class ZANA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String telefon;
    private String poruka;
    private String datum;
    private Status status;

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getTelefon() { return telefon; }

    public void setTelefon(String telefon) { this.telefon = telefon; }

    public String getPoruka() { return poruka; }

    public void setPoruka(String poruka) { this.poruka = poruka; }

    public String getDatum() { return datum; }

    public void setDatum(String datum) { this.datum = datum; }

    public Status getStatus() { return status; }

    public void setStatus(Status status) { this.status = status; }
}
