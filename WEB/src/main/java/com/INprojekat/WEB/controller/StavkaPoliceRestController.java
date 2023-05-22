package com.INprojekat.WEB.controller;

import com.INprojekat.WEB.dto.PolicaAddDto;
import com.INprojekat.WEB.dto.StavkaPoliceAddDto;
import com.INprojekat.WEB.dto.StavkaPoliceDto;
import com.INprojekat.WEB.dto.ZanrDto;
import com.INprojekat.WEB.entity.Korisnik;
import com.INprojekat.WEB.service.StavkaPoliceService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StavkaPoliceRestController {

    @Autowired
    private StavkaPoliceService stavkaPoliceService;

    @GetMapping("/api/stavka-police/{stavkaPoliceId}")
    public ResponseEntity<StavkaPoliceDto> getStavka(@PathVariable Long stavkaPoliceId){

        StavkaPoliceDto stavkaPoliceDto = stavkaPoliceService.findOne(stavkaPoliceId);

        return ResponseEntity.ok(stavkaPoliceDto);
    }
    @GetMapping("/api/stavke-police")
    public ResponseEntity<List<StavkaPoliceDto>> getStavke(HttpSession session){
        List<StavkaPoliceDto> dtos = stavkaPoliceService.findAll();
        return ResponseEntity.ok(dtos);
    }
    @PostMapping("/api/citalac/{citalacId}/polica/{policaId}/stavka-police-add")
    public ResponseEntity<?> addStavkaPoliceCitalac(@RequestBody StavkaPoliceAddDto stavkaPoliceAddDto,@PathVariable Long citalacId,@PathVariable Long policaId, HttpSession session) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("employee");
        if(loggedKorisnik.getUloga() == Korisnik.Uloga.CITALAC ){
            stavkaPoliceService.create(stavkaPoliceAddDto, policaId);
            return new ResponseEntity<>("Stavka police added successfully", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("You are not administrator", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/api/autor/{autorId}/polica/{policaId}/stavka-police-add")
    public ResponseEntity<?> addStavkaPoliceAutor(@RequestBody StavkaPoliceAddDto stavkaPoliceAddDto,@PathVariable Long autorId,@PathVariable Long policaId, HttpSession session) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("employee");
        if(loggedKorisnik.getUloga() == Korisnik.Uloga.AUTOR ){
            stavkaPoliceService.create(stavkaPoliceAddDto, policaId);
            return new ResponseEntity<>("Stavka police added successfully", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("You are not administrator", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/api/citalac/{citalacId}/polica/{policaId}/stavka-police/{id}")
    public ResponseEntity<?> deleteStavkaPoliceCitalac(@PathVariable Long citalacId,@PathVariable Long policaId,@PathVariable Long id, HttpSession session) throws ChangeSetPersister.NotFoundException {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("employee");
        if(loggedKorisnik.getUloga() == Korisnik.Uloga.CITALAC ){
            stavkaPoliceService.deleteStavkaPolice(policaId, id);
            return new ResponseEntity<>("Stavka police deleted successfully", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("You are not administrator", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/api/autor/{autorId}/polica/{policaId}/stavka-police/{id}")
    public ResponseEntity<?> deleteStavkaPoliceAutor(@PathVariable Long autorId,@PathVariable Long policaId,@PathVariable Long id, HttpSession session) throws ChangeSetPersister.NotFoundException {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("employee");
        if(loggedKorisnik.getUloga() == Korisnik.Uloga.AUTOR){
            stavkaPoliceService.deleteStavkaPolice(policaId, id);
            return new ResponseEntity<>("Stavka police deleted successfully", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("You are not administrator", HttpStatus.BAD_REQUEST);
        }
    }

}
