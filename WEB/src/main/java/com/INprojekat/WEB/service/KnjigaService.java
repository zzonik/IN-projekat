package com.INprojekat.WEB.service;


import com.INprojekat.WEB.dto.KnjigaAutorDto;
import com.INprojekat.WEB.entity.Autor;

import com.INprojekat.WEB.dto.KnjigaDto;

import com.INprojekat.WEB.entity.Knjiga;
import com.INprojekat.WEB.entity.Korisnik;
import com.INprojekat.WEB.repository.KnjigaRepository;
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
    private KorisnikService korisnikService;



    public List<Knjiga> findAll(){
        return knjigaRepository.findAll();

    @Autowired
    private KnjigaRepository knjigaRepository;

    public List<KnjigaDto> findAll(){
        List<Knjiga> knjigeList = knjigaRepository.findAll();

        List<KnjigaDto> dtos = new ArrayList<>();
        for(Knjiga knjiga : knjigeList){
            KnjigaDto dto = new KnjigaDto(knjiga);
            dtos.add(dto);
        }
        return dtos;

    }
    public Knjiga create(Long id, KnjigaAutorDto knjigaAutorDto) {
        Korisnik korisnik = korisnikService.findOne(id);
        Autor autor = new Autor();
        autor.setIme(korisnik.getIme());
        autor.setPrezime(korisnik.getPrezime());
        autor.setKorisnickoIme(korisnik.getKorisnickoIme());
        autor.setMail(korisnik.getMail());
        autor.setLozinka(korisnik.getLozinka());
        autor.setProfilnaSlika(korisnik.getProfilnaSlika());
        autor.setUloga(korisnik.getUloga());
        autor.setAktivnost(true);

        Knjiga knjiga = new Knjiga();
        knjiga.setNaslov(knjigaAutorDto.getNaslov());
        knjiga.setNaslovnaFotografija(knjigaAutorDto.getNaslovnaFotografija());
        knjiga.setISBN(knjigaAutorDto.getISBN());
        knjiga.setBrojStrana(knjigaAutorDto.getBrojStrana());
        knjiga.setDatumObjavljivanja(knjigaAutorDto.getDatumObjavljivanja());
        knjiga.setOpis(knjigaAutorDto.getOpis());
        knjiga.setAutor(autor);
        //knjiga.setZanr(knjigaAutorDto.getZanr());

        return save(knjiga);
    }
    public Knjiga save(Knjiga knjiga) {return knjigaRepository.save(knjiga);}

    public Knjiga findOne(Long id){
        Optional<Knjiga> foundKnjiga = knjigaRepository.findById(id);
        if (foundKnjiga.isPresent())
            return foundKnjiga.get();

        return null;
    }
}
