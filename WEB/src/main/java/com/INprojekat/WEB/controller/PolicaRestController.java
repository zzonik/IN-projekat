package com.INprojekat.WEB.controller;

import com.INprojekat.WEB.dto.*;
import com.INprojekat.WEB.entity.Knjiga;
import com.INprojekat.WEB.entity.Korisnik;
import com.INprojekat.WEB.entity.Polica;
import com.INprojekat.WEB.entity.Recenzija;
import com.INprojekat.WEB.repository.KorisnikRepository;
import com.INprojekat.WEB.service.KnjigaService;
import com.INprojekat.WEB.service.KorisnikService;
import com.INprojekat.WEB.service.PolicaService;
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
public class PolicaRestController {

    @Autowired
    private PolicaService policaService;

    @Autowired
    private KorisnikService korisnikService;

    @GetMapping("/api/polica/{id}")
    public ResponseEntity<PolicaDto> getPolica(@PathVariable Long id){

        PolicaDto policaDto= policaService.findOne(id);

        return ResponseEntity.ok(policaDto);
    }

    @GetMapping("/api/police")
    public ResponseEntity<List<PolicaDto>> getPolice(HttpSession session) {
        List<Polica> policeList = policaService.findAll();
        List<PolicaDto> dtos = new ArrayList<>();

        for (Polica polica : policeList) {
            PolicaDto dto = new PolicaDto(polica);
            dtos.add(dto);
        }
        return ResponseEntity.ok(dtos);
    }
    @GetMapping("/api/korisnici/{id}/police")
    public ResponseEntity<List<PolicaDto>> getPoliceKorisnika(@PathVariable Long id, HttpSession session) {
        Korisnik korisnik = korisnikService.findOne(id);
        Set<Polica> policeSet = korisnik.getPolice();
        List<PolicaDto> dtos = new ArrayList<>();

        for (Polica polica : policeSet) {
            PolicaDto dto = new PolicaDto(polica);
            dtos.add(dto);
        }
        return ResponseEntity.ok(dtos);
    }
    @GetMapping("/api/autor/{autorId}/police")
    public ResponseEntity<List<PolicaDto>> getPoliceAutora(@PathVariable Long autorId, HttpSession session) {
        Korisnik korisnik = korisnikService.findOne(autorId);
        Set<Polica> policeSet = korisnik.getPolice();
        List<PolicaDto> dtos = new ArrayList<>();

        for (Polica polica : policeSet) {
            PolicaDto dto = new PolicaDto(polica);
            dtos.add(dto);
        }
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/api/citalac/{citalacId}/police")
    public ResponseEntity<List<PolicaDto>> getPoliceCitalaca(@PathVariable Long citalacId, HttpSession session) {
        Korisnik korisnik = korisnikService.findOne(citalacId);
        Set<Polica> policeSet = korisnik.getPolice();
        List<PolicaDto> dtos = new ArrayList<>();

        for (Polica polica : policeSet) {
            PolicaDto dto = new PolicaDto(polica);
            dtos.add(dto);
        }
        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/api/citalac/{citalacId}/police-add")
    public ResponseEntity<?> addPolicaCitalac(@PathVariable Long citalacId,@RequestBody PolicaAddDto policaAddDto, HttpSession session) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("employee");
        if(citalacId == loggedKorisnik.getId()) {
            if (loggedKorisnik.getUloga() == Korisnik.Uloga.CITALAC) {
                if (policaService.existsPolica(policaAddDto.getNaziv())) {
                    return new ResponseEntity<>("Name for shelf is used!", HttpStatus.BAD_REQUEST);
                }
                policaService.create(policaAddDto, loggedKorisnik.getId());
                return new ResponseEntity<>("Shelf added successfully", HttpStatus.OK);
            }
        }
            return new ResponseEntity<>("You are not administrator", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/api/autor/{autorId}/police-add")
    public ResponseEntity<?> addPolicaAutor(@PathVariable Long autorId, @RequestBody PolicaAddDto policaAddDto, HttpSession session) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("employee");
        if(autorId == loggedKorisnik.getId()) {
            if (loggedKorisnik.getUloga() == Korisnik.Uloga.AUTOR) {
                if (policaService.existsPolica(policaAddDto.getNaziv())) {
                    return new ResponseEntity<>("Name for shelf is udes!", HttpStatus.BAD_REQUEST);
                }
                policaService.create(policaAddDto, loggedKorisnik.getId());
                return new ResponseEntity<>("Shelf added successfully", HttpStatus.OK);
            }
        }
            return new ResponseEntity<>("You are not administrator", HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/api/citalac/{citalacId}/police/{policaId}")
    public ResponseEntity<?> deletePolicaCitalac(@PathVariable Long citalacId,@PathVariable Long policaId,HttpSession session) throws ChangeSetPersister.NotFoundException {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("employee");
        if(citalacId == loggedKorisnik.getId()) {
            if (loggedKorisnik.getUloga() == Korisnik.Uloga.CITALAC) {
                if (policaService.deletePolica(citalacId, policaId) == true) {
                    return new ResponseEntity<>("Shelf deleted successfully", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Shelf is not deleted, it is primary", HttpStatus.OK);
                }
            }
        }
            return new ResponseEntity<>("You are not administrator", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/api/autor/{autorId}/police/{policaId}")
    public ResponseEntity<?> deletePolicaAutor(@PathVariable Long autorId,@PathVariable Long policaId,HttpSession session) throws ChangeSetPersister.NotFoundException {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("employee");
        if(autorId == loggedKorisnik.getId()) {
            if (loggedKorisnik.getUloga() == Korisnik.Uloga.AUTOR) {
                if (policaService.deletePolica(autorId, policaId) == true) {
                    return new ResponseEntity<>("Shelf deleted successfully", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Shelf is not deleted, it is primary ", HttpStatus.OK);
                }
            }
        }
            return new ResponseEntity<>("You are not administrator", HttpStatus.BAD_REQUEST);
    }

}
