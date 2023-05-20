package com.INprojekat.WEB.service;

import com.INprojekat.WEB.dto.KnjigaAutorDto;
import com.INprojekat.WEB.dto.KnjigaDto;
import com.INprojekat.WEB.entity.Autor;
import com.INprojekat.WEB.entity.Knjiga;
import com.INprojekat.WEB.entity.Korisnik;
import com.INprojekat.WEB.repository.KnjigaRepository;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class KnjigaService {

    @Autowired
    private KnjigaRepository knjigaRepository;

    @Autowired
    private AutorService autorService;



    public List<KnjigaDto> findAll(){
        List<Knjiga> knjigeList = knjigaRepository.findAll();

        List<KnjigaDto> dtos = new ArrayList<>();
        for(Knjiga knjiga : knjigeList){
            KnjigaDto dto = new KnjigaDto(knjiga);
            dtos.add(dto);
        }
        return dtos;

    }

    public Knjiga create(Long autorId, KnjigaAutorDto knjigaAutorDto) {
        Autor autor = autorService.findOne(autorId);
        Knjiga knjiga = new Knjiga();
        knjiga.setNaslov(knjigaAutorDto.getNaslov());
        knjiga.setNaslovnaFotografija(knjigaAutorDto.getNaslovnaFotografija());
        knjiga.setISBN(knjigaAutorDto.getISBN());
        knjiga.setBrojStrana(knjigaAutorDto.getBrojStrana());
        knjiga.setDatumObjavljivanja(knjigaAutorDto.getDatumObjavljivanja());
        knjiga.setOpis(knjigaAutorDto.getOpis());
        knjiga.setZanr(knjigaAutorDto.getZanr());

        // Add the knjiga to the autor's knjige list
        autor.addKnjiga(knjiga);

        return save(knjiga);
    }

    public Knjiga save(Knjiga knjiga) {
        return knjigaRepository.save(knjiga);
    }

    public Knjiga findOne(Long id){
        Optional<Knjiga> foundKnjiga = knjigaRepository.findById(id);
        if (foundKnjiga.isPresent())
            return foundKnjiga.get();

        return null;
    }
}