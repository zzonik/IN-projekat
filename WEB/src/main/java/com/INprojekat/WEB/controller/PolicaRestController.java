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
    @GetMapping("/api/korisnik/police")
    public ResponseEntity<List<PolicaDto>> getPoliceKorisnika(HttpSession session) {
        Korisnik korisnik = (Korisnik) session.getAttribute("employee");
        Set<Polica> policeSet = korisnik.getPolice();
        List<PolicaDto> dtos = new ArrayList<>();

        for (Polica polica : policeSet) {
            if(polica.isPrimarna()==false) {
                PolicaDto dto = new PolicaDto(polica);
                dtos.add(dto);
            }
        }
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/api/korisnik/primarne_police")
    public ResponseEntity<List<PolicaDto>> getPrimarnePoliceKorisnika(HttpSession session) {
        Korisnik korisnik = (Korisnik) session.getAttribute("employee");
        Set<Polica> policeSet = korisnik.getPolice();
        List<PolicaDto> dtos = new ArrayList<>();

        for (Polica polica : policeSet) {
            if(polica.isPrimarna()==true) {
                PolicaDto dto = new PolicaDto(polica);
                dtos.add(dto);
            }
        }
        return ResponseEntity.ok(dtos);
    }


    @GetMapping("/api/korisnik/{id}/police")
    public ResponseEntity<List<PolicaDto>> getPoliceKorisnikaSaId(@PathVariable Long id, HttpSession session) {
        Korisnik korisnik = (Korisnik) session.getAttribute("employee");
        Set<Polica> policeSet = korisnik.getPolice();
        List<PolicaDto> dtos = new ArrayList<>();

        for (Polica polica : policeSet) {
            if(polica.isPrimarna()==false) {
                PolicaDto dto = new PolicaDto(polica);
                dtos.add(dto);
            }
        }
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/api/korisnik/{id}/primarne_police")
    public ResponseEntity<List<PolicaDto>> getPrimarnePoliceKorisnikaSaId(@PathVariable Long id ,HttpSession session) {
        Korisnik korisnik = (Korisnik) session.getAttribute("employee");
        Set<Polica> policeSet = korisnik.getPolice();
        List<PolicaDto> dtos = new ArrayList<>();

        for (Polica polica : policeSet) {
            if(polica.isPrimarna()==true) {
                PolicaDto dto = new PolicaDto(polica);
                dtos.add(dto);
            }
        }
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/api/autor/{autorId}/police/primarne")
    public ResponseEntity<?> getPrimarnePoliceAutora(@PathVariable Long autorId) {
        Korisnik korisnik = korisnikService.findOne(autorId);
        Set<Polica> policeSet = korisnik.getPolice();
        List<PolicaDto> dtos = new ArrayList<>();

        for (Polica polica : policeSet) {
            if(polica.isPrimarna()==true) {
                PolicaDto dto = new PolicaDto(polica);
                dtos.add(dto);
            }
        }
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/api/autor/{autorId}/police")
    public ResponseEntity<?> getPoliceAutora(@PathVariable Long autorId) {
        Korisnik korisnik = korisnikService.findOne(autorId);
        Set<Polica> policeSet = korisnik.getPolice();
        List<PolicaDto> dtos = new ArrayList<>();

        for (Polica polica : policeSet) {
            if(polica.isPrimarna()==false) {
                PolicaDto dto = new PolicaDto(polica);
                dtos.add(dto);
            }
        }
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/api/citalac/{citalacId}/police")
    public ResponseEntity<?> getPoliceCitalaca(@PathVariable Long citalacId) {
        Korisnik korisnik = korisnikService.findOne(citalacId);
        Set<Polica> policeSet = korisnik.getPolice();
        if(citalacId == korisnik.getId()) {
            List<PolicaDto> dtos = new ArrayList<>();

            for (Polica polica : policeSet) {
                if(polica.isPrimarna()==false) {
                    PolicaDto dto = new PolicaDto(polica);
                    dtos.add(dto);
                }
            }
            return ResponseEntity.ok(dtos);
        }
        return new ResponseEntity<>("You are not administrator", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/api/citalac/{citalacId}/police/primarne")
    public ResponseEntity<?> getPrimarnePoliceCitalaca(@PathVariable Long citalacId) {
        Korisnik korisnik = korisnikService.findOne(citalacId);
        Set<Polica> policeSet = korisnik.getPolice();
        if(citalacId == korisnik.getId()) {
            List<PolicaDto> dtos = new ArrayList<>();

            for (Polica polica : policeSet) {
                if (polica.isPrimarna()==true) {
                    PolicaDto dto = new PolicaDto(polica);
                    dtos.add(dto);
                }
            }
            return ResponseEntity.ok(dtos);
        }
        return new ResponseEntity<>("You are not administrator", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/api/citalac/police-add")
    public ResponseEntity<?> addPolica(@RequestBody PolicaAddDto policaAddDto, HttpSession session) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("employee");
        Long korisnikId = loggedKorisnik.getId();
        if (loggedKorisnik.getUloga() == Korisnik.Uloga.CITALAC || loggedKorisnik.getUloga() == Korisnik.Uloga.AUTOR) {
            if (policaService.existsPolicaInKorisnik(policaAddDto.getNaziv(), korisnikId)) {
                return ResponseEntity.badRequest().body("Name for shelf is used!");
            }
            policaService.create(policaAddDto, korisnikId);
            return ResponseEntity.ok("Shelf added successfully");
        } else {
            return ResponseEntity.ok("Error adding book on shelf");
        }
    }

    @DeleteMapping("/api/citalac/polica-remove/{policaId}")
    public ResponseEntity<?> deletePolica(@PathVariable Long policaId,HttpSession session) throws ChangeSetPersister.NotFoundException {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("employee");
        if (loggedKorisnik.getUloga() == Korisnik.Uloga.CITALAC  || loggedKorisnik.getUloga() == Korisnik.Uloga.AUTOR ) {
            if (policaService.deletePolica(loggedKorisnik.getId(), policaId)) {
                return new ResponseEntity<>("Shelf deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Shelf is not deleted, it is primary", HttpStatus.OK);
            }
        } else {
        return new ResponseEntity<>("Error deleting shelf", HttpStatus.OK);
        }
    }

}
