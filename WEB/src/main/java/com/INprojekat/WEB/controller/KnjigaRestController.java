package com.INprojekat.WEB.controller;

import com.INprojekat.WEB.dto.*;
import com.INprojekat.WEB.service.KorisnikService;
import com.INprojekat.WEB.entity.Knjiga;
import com.INprojekat.WEB.entity.Korisnik;
import com.INprojekat.WEB.service.KnjigaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class KnjigaRestController {

    @Autowired
    private KnjigaService knjigaService;

    @GetMapping("/api/knjige")
    public ResponseEntity<List<KnjigaDto>> getKnjige(){
        List<KnjigaDto> dtos = knjigaService.findAll();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/api/knjiga/{id}")
    public ResponseEntity<KnjigaDto> getKnjiga(@PathVariable Long id){

        KnjigaDto knjigaDto = knjigaService.findOne(id);

        return ResponseEntity.ok(knjigaDto);
    }

    @DeleteMapping("/api/polica/{policaId}/knjiga/{knjigaId}")
    public ResponseEntity<?> deleteKnjiga(@PathVariable Long policaId,@PathVariable Long knjigaId, HttpSession session) throws ChangeSetPersister.NotFoundException {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("employee");
        if(loggedKorisnik.getUloga() == Korisnik.Uloga.CITALAC){
            knjigaService.deleteKnjiga(knjigaId,policaId, loggedKorisnik.getId());
            return ResponseEntity.noContent().build();
        }else {
            return new ResponseEntity<>("Niste citalac", HttpStatus.BAD_REQUEST);
        }

    }
}


