package com.INprojekat.WEB.controller;

import com.INprojekat.WEB.dto.KnjigaDto;
import com.INprojekat.WEB.entity.Knjiga;
import com.INprojekat.WEB.service.KnjigaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class KnjigaRestController {

    @Autowired
    private KnjigaService knjigaService;

    @GetMapping("/api/knjige")
    public ResponseEntity<List<KnjigaDto>> getKnjige(HttpSession session){
        List<Knjiga> knjigeList = knjigaService.findAll();

        List<KnjigaDto> dtos = new ArrayList<>();
        for(Knjiga knjiga : knjigeList){
            KnjigaDto dto = new KnjigaDto(knjiga);
            dtos.add(dto);
        }
        return ResponseEntity.ok(dtos);
    }

}
