package com.INprojekat.WEB.controller;

import com.INprojekat.WEB.dto.*;
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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
                return new ResponseEntity<>("Mail already used!", HttpStatus.BAD_REQUEST);
            }
            if(autorService.existsKorisnickoIme(autorRegisterDto.getKorisnickoIme())){
                return new ResponseEntity<>("Username already used!", HttpStatus.BAD_REQUEST);
            }
            if(autorService.existsLozinka(autorRegisterDto.getLozinka())){
                return new ResponseEntity<>("Password already used!", HttpStatus.BAD_REQUEST);
            }
            autorService.create(autorRegisterDto);

            return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("You are not administrator", HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/api/autor/{autorId}/add-knjiga")
    public ResponseEntity<?> addKnjiga(@PathVariable Long autorId, @RequestBody KnjigaAutorDto knjigaAutorDto, HttpSession session) {
        Object sessionEmployee = session.getAttribute("employee");

        {
            if (sessionEmployee instanceof Autor) {
                Autor loggedAutor = (Autor) sessionEmployee;
                if (autorId == loggedAutor.getId()) {
                    if (loggedAutor.getAktivnost() == false) {
                        return ResponseEntity.badRequest().body("Autor is not active");
                    }

                    Knjiga knjiga = knjigaService.create(loggedAutor.getId(), knjigaAutorDto);
                    autorService.addKnjiga(autorId, knjiga);

                    return ResponseEntity.ok("Book is added");
                }
            }

            return ResponseEntity.badRequest().body("Only Autor can add a Knjiga.");
        }
    }
    @GetMapping("/api/autor/{autorId}/get-knjige")
    public ResponseEntity<List<KnjigaDto>> getKnjige(@PathVariable Long autorId){
        List<KnjigaDto> dtos = autorService.getAutorKnjige(autorId);
        return ResponseEntity.ok(dtos);
    }
    @PutMapping("api/autor/{autorId}/knjiga/{knjigaId}/update_knjiga")
    public ResponseEntity<?> updateKnjiga(@RequestBody UpdateKnjigaDto updateKnjigaDto,@PathVariable Long autorId ,@PathVariable Long knjigaId, HttpSession session) {
        Autor loggedKorisnik = (Autor) session.getAttribute("employee");
        if(autorId == loggedKorisnik.getId()) {
            if (loggedKorisnik == null) {
                return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);
            }
            knjigaService.updateKnjiga(autorId, knjigaId, updateKnjigaDto);
        }
        return new ResponseEntity<>("Knjiga updateded successfully", HttpStatus.OK);
    }

}
