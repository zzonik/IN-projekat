package com.INprojekat.WEB.controller;

import com.INprojekat.WEB.dto.*;
import com.INprojekat.WEB.entity.Knjiga;
import com.INprojekat.WEB.entity.Korisnik;
import com.INprojekat.WEB.entity.Polica;
import com.INprojekat.WEB.entity.Recenzija;
import com.INprojekat.WEB.repository.PolicaRepository;
import com.INprojekat.WEB.service.PolicaService;
import com.INprojekat.WEB.service.RecenzijaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RecenzijaRestController {
    @Autowired
    private RecenzijaService recenzijaService;
    @Autowired
    private PolicaService policaService;

    @GetMapping("/api/recenzije")
    public ResponseEntity<List<RecenzijaDto>> getRecenzije(HttpSession session){
        List<RecenzijaDto> dtos = recenzijaService.findAll();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/api/recenzija/{id}")
    public ResponseEntity<Recenzija> getRecenzija(@PathVariable Long id){

        Recenzija recenzija = recenzijaService.findOne(id);

        return ResponseEntity.ok(recenzija);
    }

    @PostMapping("/api/citalac/{citalacId}/polica/{policaId}/stavka-police/{stavkaPoliceId}/add-recenzija")
    public ResponseEntity<?> addRecenzijaCitalac(@PathVariable Long citalacId, @PathVariable Long policaId,@PathVariable Long stavkaPoliceId ,@RequestBody RecenzijaAddDto recenzijaAddDto,HttpSession session) throws ChangeSetPersister.NotFoundException {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("employee");
        if(citalacId == loggedKorisnik.getId()){
            if(loggedKorisnik.getUloga() == Korisnik.Uloga.CITALAC ) {
                PolicaDto policaDto = policaService.findOne(policaId);
                    if (policaDto.getNaziv().equals("Read")) {
                        recenzijaService.add(recenzijaAddDto, stavkaPoliceId);
                        return new ResponseEntity<>("Recenzija added successfully", HttpStatus.OK);
                    }
            }
        }
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/api/autor/{autorId}/polica/{policaId}/stavka-police/{stavkaPoliceId}/add-recenzija")
    public ResponseEntity<?> addRecenzijaAutor(@PathVariable Long autorId, @PathVariable Long policaId,@PathVariable Long stavkaPoliceId ,@RequestBody RecenzijaAddDto recenzijaAddDto,HttpSession session) throws ChangeSetPersister.NotFoundException {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("employee");
        if(autorId == loggedKorisnik.getId()) {
            if (loggedKorisnik.getUloga() == Korisnik.Uloga.AUTOR) {
                PolicaDto policaDto = policaService.findOne(policaId);
                    if (policaDto.getNaziv().equals("Read")) {
                        recenzijaService.add(recenzijaAddDto, stavkaPoliceId);
                        return new ResponseEntity<>("Recenzija added successfully", HttpStatus.OK);
                    }
            }
        }
        return ResponseEntity.noContent().build();
    }
    @PutMapping("api/citalac/{citalacId}/recenzija/{recenzijaId}/update-recenzija")
    public ResponseEntity<?> updateRecenzijaCitalac(@PathVariable Long citalacId,@PathVariable Long recenzijaId, @RequestBody UpdateRecDto updateRecDto,HttpSession session) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("employee");
        if(citalacId == loggedKorisnik.getId()) {
            if (loggedKorisnik.getUloga() == Korisnik.Uloga.CITALAC) {
                recenzijaService.updateRecenzija(recenzijaId, updateRecDto);
                return new ResponseEntity<>("Recenzija updated successfully", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("You are not administrator", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("api/autor/{autorId}/recenzija/{recenzijaId}/update-recenzija")
    public ResponseEntity<?> updateRecenzijaAutor(@PathVariable Long autorId,@PathVariable Long recenzijaId, @RequestBody UpdateRecDto updateRecDto,HttpSession session) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("employee");
        if(autorId == loggedKorisnik.getId()) {
            if (loggedKorisnik.getUloga() == Korisnik.Uloga.AUTOR) {
                recenzijaService.updateRecenzija(recenzijaId, updateRecDto);
                return new ResponseEntity<>("Recenzija updated successfully", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("You are not administrator", HttpStatus.BAD_REQUEST);
    }
/*
    @DeleteMapping("/api/citalac/{id}/recenzija/{id}")
    public ResponseEntity<Void> deleteRecenzija(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {

        recenzijaService.deleteRecenzija(id);

        return ResponseEntity.noContent().build();
    }

*/
}
