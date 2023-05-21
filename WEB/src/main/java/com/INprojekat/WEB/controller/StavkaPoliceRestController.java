package com.INprojekat.WEB.controller;

import com.INprojekat.WEB.dto.PolicaAddDto;
import com.INprojekat.WEB.dto.StavkaPoliceAddDto;
import com.INprojekat.WEB.dto.StavkaPoliceDto;
import com.INprojekat.WEB.dto.ZanrDto;
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

    @GetMapping("/api/stavke-police")
    public ResponseEntity<List<StavkaPoliceDto>> getStavke(HttpSession session){
        List<StavkaPoliceDto> dtos = stavkaPoliceService.findAll();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/api/stavka-police{id}")
    public ResponseEntity<StavkaPoliceDto> getStavka(@PathVariable Long id){

        StavkaPoliceDto stavkaPoliceDto = stavkaPoliceService.findOne(id);

        return ResponseEntity.ok(stavkaPoliceDto);
    }

    @PostMapping("/api/stavka-police-add")
    public ResponseEntity<?> addStavkaPolice(@RequestBody StavkaPoliceAddDto stavkaPoliceAddDto) {
/*
        if (stavkaPoliceService.existsStavkaPolice(stavkaPoliceAddDto.getNaziv())) {
            return new ResponseEntity<>("Ime police je zauzeto!", HttpStatus.BAD_REQUEST);
        }
*/
        stavkaPoliceService.create(stavkaPoliceAddDto);

        return new ResponseEntity<>("Shelf added successfully", HttpStatus.OK);

    }
    @DeleteMapping("/api/stavka-police{id}")
    public ResponseEntity<Void> deleteStavkaPolice(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {

        stavkaPoliceService.deleteStavkaPolice(id);

        return ResponseEntity.noContent().build();
    }

}
