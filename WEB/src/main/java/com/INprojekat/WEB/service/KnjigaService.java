package com.INprojekat.WEB.service;


import com.INprojekat.WEB.dto.KnjigaAutorDto;
import com.INprojekat.WEB.dto.KnjigaDto;
import com.INprojekat.WEB.dto.UpdateKnjigaDto;
import com.INprojekat.WEB.entity.*;
import com.INprojekat.WEB.repository.AutorRepository;
import com.INprojekat.WEB.repository.KnjigaRepository;
import com.INprojekat.WEB.repository.ZanrRepository;
import jakarta.persistence.GeneratedValue;

import com.INprojekat.WEB.dto.*;
import com.INprojekat.WEB.entity.*;
import com.INprojekat.WEB.repository.KnjigaRepository;
import com.INprojekat.WEB.repository.PolicaRepository;
import com.INprojekat.WEB.repository.RecenzijaRepository;
import com.INprojekat.WEB.repository.StavkaPoliceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class KnjigaService {

    @Autowired
    private KnjigaRepository knjigaRepository;
    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private ZanrRepository zanrRepository;
    @Autowired
    private RecenzijaRepository recenzijaRepository;

    @Autowired
    private StavkaPoliceRepository stavkaPoliceRepository;

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private PolicaService policaService;

    @Autowired
    private AutorService autorService;

    @Autowired
    private StavkaPoliceService stavkaPoliceService;

    public KnjigaDto findOne(Long id){
        Optional<Knjiga> foundKnjiga = knjigaRepository.findById(id);
        if (foundKnjiga.isPresent()) {
            return new KnjigaDto(foundKnjiga.get());
        }
        return null;
    }

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
        knjiga.setZanr(zanrRepository.findZanrById(knjigaAutorDto.getZanrId()));
        knjiga.setAutor(autorRepository.findAutorById(knjigaAutorDto.getAutorId()));

        return save(knjiga);
    }

    public Knjiga createKnjigaAdmin(KnjigaAutorDto knjigaAutorDto) {
        Knjiga knjiga = new Knjiga();
        knjiga.setNaslov(knjigaAutorDto.getNaslov());
        knjiga.setNaslovnaFotografija(knjigaAutorDto.getNaslovnaFotografija());
        knjiga.setISBN(knjigaAutorDto.getISBN());
        knjiga.setBrojStrana(knjigaAutorDto.getBrojStrana());
        knjiga.setDatumObjavljivanja(knjigaAutorDto.getDatumObjavljivanja());
        knjiga.setOpis(knjigaAutorDto.getOpis());
        knjiga.setZanr(zanrRepository.findZanrById(knjigaAutorDto.getZanrId()));
        knjiga.setAutor(autorRepository.findAutorById(knjigaAutorDto.getAutorId()));

        return save(knjiga);
    }

    public void deleteKnjiga(Long id,Long id2, Long id3) throws ChangeSetPersister.NotFoundException {
        Knjiga knjiga = knjigaRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        Korisnik korisnik = korisnikService.findOne(id3);
        PolicaDto policaDto = policaService.findOne(id2);
        Set<Polica> police = korisnik.getPolice();
        if(policaDto.isPrimarna()){
            for(Polica polica: police){
                for(StavkaPolice stavka: polica.getStavkePolica()){
                    if(stavka.getKnjiga().getId() == id) {
                        stavkaPoliceService.deleteStavkaPolice(id);
                    }
                }
            }
        }
        stavkaPoliceService.deleteStavkaPolice(id);
    }

    public Boolean existsKnjiga(String naziv) { return knjigaRepository.existsByNaslov(naziv); }

    public Knjiga save(Knjiga knjiga) { return knjigaRepository.save(knjiga); }

    public Knjiga updateKnjiga(Long autorId, Long knjigaId, UpdateKnjigaDto updateKnjigaDto){
        Optional<Knjiga> knjiga = knjigaRepository.findById(knjigaId);
        if(knjiga.get().getAutor().getId() != autorId){
            return null;
        }
        knjiga.get().setNaslov(updateKnjigaDto.getNaslov());
        knjiga.get().setNaslovnaFotografija(updateKnjigaDto.getNaslovnaFotografija());

        String updatedISBN = updateKnjigaDto.getISBN();
        if (updatedISBN != null && !updatedISBN.isEmpty()) {
            knjiga.get().setISBN(updatedISBN);
        }
        knjiga.get().setDatumObjavljivanja(updateKnjigaDto.getDatumObjavljivanja());
        knjiga.get().setBrojStrana(updateKnjigaDto.getBrojStrana());
        knjiga.get().setOpis(updateKnjigaDto.getOpis());
        knjiga.get().setZanr(zanrRepository.findZanrById(updateKnjigaDto.getZanrId()));
        return save(knjiga.get());
    }
    public Knjiga updateKnjigaAdmin(Long knjigaId, UpdateKnjigaDto updateKnjigaDto){
        Optional<Knjiga> knjiga = knjigaRepository.findById(knjigaId);
        knjiga.get().setNaslov(updateKnjigaDto.getNaslov());
        knjiga.get().setNaslovnaFotografija(updateKnjigaDto.getNaslovnaFotografija());

        String updatedISBN = updateKnjigaDto.getISBN();
        if (updatedISBN != null && !updatedISBN.isEmpty()) {
            knjiga.get().setISBN(updatedISBN);
        }
        knjiga.get().setDatumObjavljivanja(updateKnjigaDto.getDatumObjavljivanja());
        knjiga.get().setBrojStrana(updateKnjigaDto.getBrojStrana());
        knjiga.get().setOpis(updateKnjigaDto.getOpis());
        knjiga.get().setZanr(zanrRepository.findZanrById(updateKnjigaDto.getZanrId()));
        return save(knjiga.get());
    }
}

