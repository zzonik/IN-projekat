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

@RestController
public class KnjigaRestController {

    @Autowired
    private KnjigaService knjigaService;

    @GetMapping("/api/knjige")
    public ResponseEntity<List<KnjigaDto>> getKnjige(){
        List<KnjigaDto> dtos = knjigaService.findAll();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/api/knjiga{id}")
    public ResponseEntity<KnjigaDto> getKnjiga(@PathVariable Long id){

        KnjigaDto knjigaDto = knjigaService.findOne(id);

        return ResponseEntity.ok(knjigaDto);
    }

    @PostMapping("/api/autor/post-knjiga")
    public ResponseEntity<?> postKnjiga(@PathVariable Long id,@RequestBody KnjigaAutorDto knjigaAutorDto) {

        knjigaService.create(id,knjigaAutorDto);

        return new ResponseEntity<>("Book posted successfully", HttpStatus.OK);

    }

    @PostMapping("/api/knjiga-add")
    public ResponseEntity<?> addKnjiga(@PathVariable Long id,@PathVariable Long id2,@RequestBody KnjigaAddDto knjigaAddDto) {

        if (knjigaService.existsKnjiga(knjigaAddDto.getNaslov())) {
            return new ResponseEntity<>("Book is already added!", HttpStatus.BAD_REQUEST);
        }

       // knjigaService.add(id,id2,knjigaAddDto);

        return new ResponseEntity<>("Book added successfully", HttpStatus.OK);

    }
    @DeleteMapping("/api/knjiga{id}")
    public ResponseEntity<Void> deleteKnjiga(@PathVariable Long id,@PathVariable Long id2) throws ChangeSetPersister.NotFoundException {

        knjigaService.deleteKnjiga(id,id2);

        return ResponseEntity.noContent().build();
    }
}


