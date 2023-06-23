package com.INprojekat.WEB.controller;

import com.INprojekat.WEB.dto.*;
import com.INprojekat.WEB.entity.*;
import com.INprojekat.WEB.repository.PolicaRepository;
import com.INprojekat.WEB.service.KnjigaService;
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
import java.util.Set;

@RestController
public class RecenzijaRestController {
    @Autowired
    private RecenzijaService recenzijaService;
    @Autowired
    private PolicaService policaService;
    @Autowired
    private KnjigaService knjigaService;

    @GetMapping("/api/recenzije")
    public ResponseEntity<List<RecenzijaDto>> getRecenzije(HttpSession session){
        List<RecenzijaDto> dtos = recenzijaService.findAll();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/api/recenzija/{id}")
    public ResponseEntity<RecenzijaDto> getRecenzija(@PathVariable Long id){

        Recenzija recenzija = recenzijaService.findOne(id);
        RecenzijaDto dto = new RecenzijaDto(recenzija);

        return ResponseEntity.ok(dto);
    }

    @PostMapping("/api/citalac/polica/{policaId}/stavka-police/add-recenzija")
    public ResponseEntity<?> addRecenzija(@PathVariable Long policaId,@RequestBody RecenzijaAddDto recenzijaAddDto,HttpSession session) throws ChangeSetPersister.NotFoundException {
        PolicaDto policaDto = policaService.findOne(policaId);
            if (policaDto.getNaziv().equals("Read")) {
                Set<StavkaPolice> stavke = policaDto.getStavkePolica();
                for(StavkaPolice stavka: stavke){
                    if(stavka.getKnjiga().getId() == recenzijaAddDto.getKnjigaId()){
                        Long id = stavka.getId();
                        recenzijaService.add(recenzijaAddDto, id);
                        return new ResponseEntity<>("Recenzija added successfully", HttpStatus.OK);
                    }
                }
            }
        return null;
    }
    @PutMapping("api/citalac/recenzija/{recenzijaId}/update-recenzija")
    public ResponseEntity<?> updateRecenzija(@PathVariable Long recenzijaId, @RequestBody UpdateRecDto updateRecDto,HttpSession session) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("employee");
        if (loggedKorisnik.getUloga() == Korisnik.Uloga.CITALAC  || loggedKorisnik.getUloga() == Korisnik.Uloga.AUTOR ) {
            recenzijaService.updateRecenzija(recenzijaId, updateRecDto);
            return new ResponseEntity<>("Recenzija updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error updating Recenzija", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/api/korisnik/recenzija/{id}")
    public ResponseEntity<Void> deleteRecenzija(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {

        recenzijaService.deleteRecenzija(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("api/search-recenzijeKnjige/{knjigaId}")
    public ResponseEntity<?> searchRecenzijeKnjige(@PathVariable Long knjigaId) {
        Set<RecenzijaDto> recenzije = recenzijaService.searchRecenzijeKnjige(knjigaId);
        if (recenzije.isEmpty()) {
            return ResponseEntity.badRequest().body("Ne postoji");
        } else {
            return ResponseEntity.ok(recenzije);
        }
    }

}
