package com.INprojekat.WEB.service;

import com.INprojekat.WEB.dto.KnjigaAutorDto;
import com.INprojekat.WEB.dto.KnjigaDto;
import com.INprojekat.WEB.dto.UpdateKnjigaDto;
import com.INprojekat.WEB.entity.*;
import com.INprojekat.WEB.repository.AutorRepository;
import com.INprojekat.WEB.repository.KnjigaRepository;
import com.INprojekat.WEB.repository.ZanrRepository;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class KnjigaService {

    @Autowired
    private KnjigaRepository knjigaRepository;

    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private ZanrRepository zanrRepository;



    public List<KnjigaDto> findAll(){
        List<Knjiga> knjigeList = knjigaRepository.findAll();

        List<KnjigaDto> dtos = new ArrayList<>();
        for(Knjiga knjiga : knjigeList){
            KnjigaDto dto = new KnjigaDto(knjiga);
            dtos.add(dto);
        }
        return dtos;
    }


    public Knjiga create(KnjigaAutorDto knjigaAutorDto) {
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

    public Knjiga save(Knjiga knjiga) {
        return knjigaRepository.save(knjiga);
    }

    public Knjiga findOne(Long id){
        Optional<Knjiga> foundKnjiga = knjigaRepository.findById(id);
        if (foundKnjiga.isPresent())
            return foundKnjiga.get();

        return null;
    }
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
    public void deleteKnjiga(Long id) throws ChangeSetPersister.NotFoundException {
        Knjiga knjiga = knjigaRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        if(knjiga.getRecenzije().isEmpty()) {
            for (StavkaPolice stavka : knjiga.getStavka_polica()) {
                stavka.setKnjiga(null); // Remove the association to avoid constraint violation
                knjigaRepository.delete(knjiga);
            }
        }
    }
}