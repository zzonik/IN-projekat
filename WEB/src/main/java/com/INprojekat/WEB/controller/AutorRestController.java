package com.INprojekat.WEB.controller;

import com.INprojekat.WEB.dto.AutorRegisterDto;
import com.INprojekat.WEB.dto.KnjigaAutorDto;
import com.INprojekat.WEB.entity.Autor;
import com.INprojekat.WEB.entity.Knjiga;
import com.INprojekat.WEB.entity.Korisnik;
import com.INprojekat.WEB.service.AutorService;
import com.INprojekat.WEB.service.KnjigaService;
import jakarta.servlet.http.HttpSession;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutorRestController {

    @Autowired
    private AutorService autorService;

    @Autowired
    private KnjigaService knjigaService;

    @PostMapping("api/autor-register")
    public ResponseEntity<?> registerAutor(@RequestBody AutorRegisterDto autorRegisterDto, HttpSession session){
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("employee");
        if(loggedKorisnik.getUloga() == Korisnik.Uloga.ADMINISTRATOR){
            if(autorService.existsMail(autorRegisterDto.getMail())){
                return new ResponseEntity<>("Mail je zauzet!", HttpStatus.BAD_REQUEST);
            }
            if(autorService.existsKorisnickoIme(autorRegisterDto.getMail())){
                return new ResponseEntity<>("Korisnicko ime je zauzeto!", HttpStatus.BAD_REQUEST);
            }
            if(autorService.existsLozinka(autorRegisterDto.getLozinka())){
                return new ResponseEntity<>("Loznika je zauzeta!", HttpStatus.BAD_REQUEST);
            }
            autorService.create(autorRegisterDto);

            return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Niste administrator", HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/api/autor/add-knjiga")
    public ResponseEntity<?> addKnjiga(@RequestBody KnjigaAutorDto knjigaAutorDto, HttpSession session) {
        Object sessionEmployee = session.getAttribute("employee");

        if (sessionEmployee instanceof Autor) {
            Autor loggedAutor = (Autor) sessionEmployee;

            // Create a new Knjiga
            Knjiga knjiga = new Knjiga();
            knjiga.setNaslov(knjigaAutorDto.getNaslov());
            knjiga.setNaslovnaFotografija(knjigaAutorDto.getNaslovnaFotografija());
            knjiga.setISBN(knjigaAutorDto.getISBN());
            knjiga.setBrojStrana(knjigaAutorDto.getBrojStrana());
            knjiga.setDatumObjavljivanja(knjigaAutorDto.getDatumObjavljivanja());
            knjiga.setOpis(knjigaAutorDto.getOpis());
            knjiga.setZanr(knjigaAutorDto.getZanr());

            // Set the Autor for the Knjiga
            knjiga.setAutor(loggedAutor);

            // Save the Knjiga
            knjigaService.save(knjiga);

            // Add the Knjiga to the knjige list of the Autor
            loggedAutor.getKnjige().add(knjiga);

            return ResponseEntity.ok("Knjiga dodata");
        }

        return ResponseEntity.badRequest().body("Only Autor can add a Knjiga.");
    }



}
