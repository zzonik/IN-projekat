package com.INprojekat.WEB.configuration;

import com.INprojekat.WEB.entity.*;
import com.INprojekat.WEB.repository.*;
import com.INprojekat.WEB.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
        Korisnik autorK1 = new Korisnik("Mićo", "Roknić", "Mico1389", "micomico@gmail.com", "brizaou",  "/dejdo", "lud", Korisnik.Uloga.AUTOR);
        Korisnik autorK2 = new Korisnik("Dule", "Savić", "Dule_Rana", "dulesavic55@gmail.com", "1234crvenkapa",  "/slika1.jpg", "brz", Korisnik.Uloga.AUTOR);

        Autor autor1 = new Autor(autorK1, false);
        Autor autor2 = new Autor(autorK2, false);

        Korisnik admin = new Korisnik("Milka", "Canic", "milkica", "milka123@gmail.com", "milka32",  "/dobro", "svemoguca", Korisnik.Uloga.ADMINISTRATOR);
        korisnikRepository.saveAll(
                List.of(admin)
        );
        autorRepository.saveAll(
                List.of(autor1, autor2)
        );

        Zanr zanr1 = new Zanr("Drama");
        Zanr zanr2 = new Zanr("Triler");
        zanrRepository.saveAll(List.of(zanr1, zanr2));

        DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        Date d1 = df.parse("12-10-2011"); // for example, today's date
        Date d2 = df.parse("01-01-1900"); // use your own dates, of course

        Knjiga knjiga1 = new Knjiga("Braca Karamazovi", "/23", "132421", 943, "Jako duga knjiga", d1, autor1, zanr1);
        Knjiga knjiga2 = new Knjiga("Naruto", "slika.jpg", "E821932", 44, "Manga",d2 , autor1, zanr2);
        knjigaRepository.saveAll(List.of(knjiga1, knjiga2));
        return true;
    }
}

