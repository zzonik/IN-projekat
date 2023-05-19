package com.INprojekat.WEB.controller;

import com.INprojekat.WEB.dto.KnjigaDto;
import com.INprojekat.WEB.dto.PolicaDto;
import com.INprojekat.WEB.entity.Knjiga;
import com.INprojekat.WEB.entity.Polica;
import com.INprojekat.WEB.service.KnjigaService;
import com.INprojekat.WEB.service.PolicaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PolicaRestController {

    @Autowired
    private PolicaService policaService;

    @GetMapping("/api/police")
    public ResponseEntity<List<PolicaDto>> getKnjige(HttpSession session) {
        List<Polica> policeList = policaService.findAll();
        List<PolicaDto> dtos = new ArrayList<>();

        for (Polica polica : policeList) {
            PolicaDto dto = new PolicaDto(polica);
            dtos.add(dto);
        }
        return ResponseEntity.ok(dtos);
    }
    @GetMapping("/api/police/add")
    public ResponseEntity<?> addPolica(PolicaDto policaDto) {

        if (policaService.existsPolica(policaDto.getNaziv())) {
            return new ResponseEntity<>("Ime police je zauzeto!", HttpStatus.BAD_REQUEST);
        }

        policaService.create(policaDto);

        return new ResponseEntity<>("Shelf added successfully", HttpStatus.OK);

    }
    @DeleteMapping("/api/police/{id}")
    public ResponseEntity<Void> deletePolica(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {

        policaService.deletePolica(id);

        return ResponseEntity.noContent().build();
    }

}
