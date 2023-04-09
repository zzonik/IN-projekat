package com.INprojekat.WEB.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.data.annotation.AccessType;

import java.util.Date;

@Entity
public class Knjiga {
    @Id
    private Long id;
    private String naslov;
    private Object naslovnaFotografija;
    private String ISBN;
    private Date datumObjavljivanja;
    private int brojStrana;
    private String opis;
    private Zanr zanr;
    private Double ocena;
}
