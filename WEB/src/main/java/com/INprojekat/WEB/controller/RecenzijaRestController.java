package com.INprojekat.WEB.controller;

import com.INprojekat.WEB.dto.*;
import com.INprojekat.WEB.entity.Knjiga;
import com.INprojekat.WEB.entity.Korisnik;
import com.INprojekat.WEB.entity.Recenzija;
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
//izmena #2
    @GetMapping("/api/recenzije")
    public ResponseEntity<List<RecenzijaDto>> getRecenzije(HttpSession session){
        List<RecenzijaDto> dtos = recenzijaService.findAll();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/api/recenzija{id}")
    public ResponseEntity<Recenzija> getRecenzija(@PathVariable Long id){

        Recenzija recenzija = recenzijaService.findOne(id);

        return ResponseEntity.ok(recenzija);
    }

    @PostMapping("/api/recenzija-add")
    public ResponseEntity<?> addRecenzija(@RequestBody RecenzijaDto recenzijaDto) {

        recenzijaService.add(recenzijaDto);

        return new ResponseEntity<>("Recenzija added successfully", HttpStatus.OK);

    }

    @PutMapping("api/update_recenzija")
    public ResponseEntity<?> updateRecenzija(@PathVariable Long id, @RequestBody UpdateRecDto updateRecDto) {

        recenzijaService.updateRecenzija(id, updateRecDto);

        return new ResponseEntity<>("Recenzija updateded successfully", HttpStatus.OK);

    }

    @DeleteMapping("/api/recenzija/{id}")
    public ResponseEntity<Void> deleteRecenzija(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {

        recenzijaService.deleteRecenzija(id);

        return ResponseEntity.noContent().build();
    }


}
