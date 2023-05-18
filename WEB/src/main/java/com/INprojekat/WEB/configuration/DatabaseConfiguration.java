package com.INprojekat.WEB.configuration;

import com.INprojekat.WEB.entity.Korisnik;
import com.INprojekat.WEB.entity.Recenzija;
import com.INprojekat.WEB.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Configuration
public class DatabaseConfiguration {
    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private KnjigaRepository knjigaRepository;
    @Autowired
    private KorisnikRepository korisnikRepository;
    @Autowired
    private PolicaRepository policaRepository;
    @Autowired
    private RecenzijaRepository recenzijaRepository;
    @Autowired
    private StavkaPoliceRepository stavkaPoliceRepository;
    @Autowired
    private ZahtevZaAktiavcijuNalogaAutoraRepository zahtevZaAktiavcijuNalogaAutoraRepository;
    @Autowired
    private ZanrRepository zanrRepository;

    @Bean
    public boolean instantiate(){
        return true;
    }
}
