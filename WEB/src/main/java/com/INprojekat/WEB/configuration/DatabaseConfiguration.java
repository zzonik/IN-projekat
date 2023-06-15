package com.INprojekat.WEB.configuration;

import com.INprojekat.WEB.entity.*;
import com.INprojekat.WEB.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public boolean instantiate() throws ParseException {
        Korisnik ker = new Korisnik("Dejan", "Bodiroga", "rogxon", "bodirogaroga@gmail.com", "nabodiroga",  "/dejo", Korisnik.Uloga.CITALAC);

        Korisnik autorK1 = new Korisnik("Mico", "Roknic", "dsaf", "micomico@gmail.com", "brizaou",  "/dejdo", Korisnik.Uloga.AUTOR);
        Korisnik autorK2 = new Korisnik("Dule", "Savic", "Dule_Rana", "dulesavic55@gmail.com", "1234crvenkapa",  "/slika1.jpg", Korisnik.Uloga.AUTOR);

        Autor autor1 = new Autor(autorK1, false);
        Autor autor2 = new Autor(autorK2, false);

        Korisnik admin = new Korisnik("Milka", "Canic", "milkica", "milka123@gmail.com", "milka32",  "/dobro", Korisnik.Uloga.ADMINISTRATOR);
        korisnikRepository.saveAll(
                List.of(ker, admin)
        );
        autorRepository.saveAll(
                List.of(autor1, autor2)
        );

        Polica polica1 = new Polica("Want to Read", true );
        Polica polica2 = new Polica("Currently Reading", true );
        Polica polica3 = new Polica("Read", true );
        policaRepository.saveAll(
                List.of(polica1, polica2, polica3)
        );

        Zanr zanr1 = new Zanr("Drama");
        Zanr zanr2 = new Zanr("Triler");
        zanrRepository.saveAll(List.of(zanr1, zanr2));

        Knjiga knjiga1 = new Knjiga("Braca Karamazovi", "/23", "132421", 943, "Najjjjjaca");
        knjigaRepository.save(knjiga1);
        return true;
    }
}

