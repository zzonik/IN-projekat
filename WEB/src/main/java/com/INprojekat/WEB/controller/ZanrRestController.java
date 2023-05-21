package com.INprojekat.WEB.controller;

import com.INprojekat.WEB.dto.RecenzijaDto;
import com.INprojekat.WEB.dto.ZanrDto;
import com.INprojekat.WEB.entity.Recenzija;
import com.INprojekat.WEB.entity.Zanr;
import com.INprojekat.WEB.service.ZanrService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ZanrRestController {

    @Autowired
    private ZanrService zanrService;

    @GetMapping("/api/zanrovi")
    public ResponseEntity<List<ZanrDto>> getZanrovi(HttpSession session){
        List<ZanrDto> dtos = zanrService.findAll();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/api/zanr{id}")
    public ResponseEntity<ZanrDto> getZanr(@PathVariable Long id){

        ZanrDto zanrDto = zanrService.findOne(id);

        return ResponseEntity.ok(zanrDto);
    }



}
