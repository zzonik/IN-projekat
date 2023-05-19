package com.INprojekat.WEB.controller;

import com.INprojekat.WEB.dto.AutorRegisterDto;
import com.INprojekat.WEB.dto.KnjigaAutorDto;
import com.INprojekat.WEB.entity.Korisnik;
import com.INprojekat.WEB.service.AutorService;
import com.INprojekat.WEB.service.KnjigaService;
import jakarta.servlet.http.HttpSession;
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
    KnjigaService knjigaService;

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
    @PostMapping("api/autor/add-knjigu")
    public ResponseEntity<?> addKnjigu(@RequestBody KnjigaAutorDto knjigaAutorDto, HttpSession session){
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("employee");

        knjigaService.create(loggedKorisnik.getId(), knjigaAutorDto);
        return new ResponseEntity<>("Knjiga dodata", HttpStatus.OK);
    }

}
