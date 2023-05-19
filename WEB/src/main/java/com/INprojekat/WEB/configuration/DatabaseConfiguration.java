package com.INprojekat.WEB.configuration;

import com.INprojekat.WEB.entity.Korisnik;
import com.INprojekat.WEB.entity.Polica;
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
<<<<<<< Updated upstream
=======

        Korisnik ker = new Korisnik("Dejan", "Bodiroga", "rogxon", "bodirogaroga@gmail.com", "nabodiroga",  "/dejo", Korisnik.Uloga.CITALAC);
        Korisnik admin = new Korisnik("Milka", "Canic", "milkica", "milka123@gmail.com", "milka32",  "/dobro", Korisnik.Uloga.ADMINISTRATOR);

        korisnikRepository.saveAll(
                List.of(ker, admin)
        );

<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
        Polica polica1 = new Polica("Want to Read", true );
        Polica polica2 = new Polica("Currently Reading", true );
        Polica polica3 = new Polica("Read", true );
        policaRepository.saveAll(
                List.of(polica1, polica2, polica3)
        );
>>>>>>> Stashed changes
        return true;
    }
}
