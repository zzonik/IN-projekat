package com.INprojekat.WEB.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class Autor {
    @Id
    private Long id;
    private boolean aktivnost;
    private List<Knjiga> knjige;
}
